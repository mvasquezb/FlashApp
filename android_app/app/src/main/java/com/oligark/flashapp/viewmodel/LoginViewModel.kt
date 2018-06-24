package com.oligark.flashapp.viewmodel

import android.app.Activity
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.oligark.flashapp.R
import com.oligark.flashapp.di.Dependencies
import com.oligark.flashapp.model.User
import com.oligark.flashapp.service.api.LoginRequest
import com.oligark.flashapp.service.api.LoginRequestPayload
import com.oligark.flashapp.service.api.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    enum class LoginType {
        EMAIL,
        GOOGLE,
        FB
    }

    enum class LoginStatus {
        NONE,
        LOADING,
        COMPLETE,
        ERROR,
        SUCCESS,
    }

    companion object {
        @JvmField val RC_GOOGLE_SIGN_IN = 12412
    }

    var email = ""
    var password = ""
    var errorMessage: String? = null
    val loginStatus = MutableLiveData<LoginStatus>()
    val currentUser = MutableLiveData<User>()
    val googleSignInProgress = MutableLiveData<Boolean>()
    val potentialUser = MutableLiveData<User>()
    val googleSignInIntent: Intent
        get() = Dependencies.getInstance().googleSignInClient(getApplication()).signInIntent

    init {
        loginStatus.value = LoginStatus.NONE
    }

    fun onLogin(loginType: LoginType) {
        loginStatus.value = LoginStatus.LOADING
        when (loginType) {
            LoginType.EMAIL -> {
                if (email.isEmpty() || password.isEmpty()) {
                    errorMessage = getApplication<Application>().getString(R.string.email_login_fields_error)
                    loginStatus.value = LoginStatus.COMPLETE
                    loginStatus.value = LoginStatus.ERROR
                    return
                }
                Dependencies.getInstance().userService.loginUser(LoginRequest(
                        loginType.toString().toLowerCase(),
                        LoginRequestPayload(email, password)
                )).enqueue(object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                        println(t?.localizedMessage)
                        t?.printStackTrace()
                        errorMessage = "Ocurrió un error"
                        handleLoginError()
                    }

                    override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                        val res = response?.body()
                        if (response == null || !response.isSuccessful
                                || res == null || res.code != 200) {
                            println("Response not successful. Code: ${response?.code()}. Message: ${response?.message()}")
                            errorMessage = when (res?.code) {
                                401 -> "Ha ingresado un usuario y/o contraseña incorrecto"
                                else -> "Ocurrió un error"
                            }
                            loginStatus.value = LoginStatus.ERROR
                            return
                        }
                        handleLoginSuccess(res.user!!)
                    }
                })
            }
            LoginType.FB -> {
                // TODO: FB login
                loginStatus.value = LoginStatus.COMPLETE
                loginStatus.value = LoginStatus.SUCCESS
            }
            LoginType.GOOGLE -> {
                googleSignInProgress.value = true
            }
        }
    }

    private fun handleLoginSuccess(user: User) {
        loginStatus.value = LoginStatus.COMPLETE
        currentUser.value = user
        loginStatus.value = LoginStatus.SUCCESS
    }

    private fun handleLoginError() {
        loginStatus.value = LoginStatus.COMPLETE
        loginStatus.value = LoginStatus.ERROR
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) {
            System.err.println("Result not ok")
            return
        }
        when (requestCode) {
            RC_GOOGLE_SIGN_IN -> {
                attemptGoogleSignIn(data)
            }
        }
    }

    private fun attemptGoogleSignIn(data: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            val user = User(
                    firstName = account.givenName,
                    firstSurname = account.familyName,
                    email = account.email,
                    imgUrl = account.photoUrl.toString(),
                    googleToken = account.serverAuthCode
            )
            Dependencies.getInstance().userService.loginUser(LoginRequest(
                    LoginType.GOOGLE.toString().toLowerCase(),
                    LoginRequestPayload(email = account.email, token = account.serverAuthCode)
            )).enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                    loginStatus.value = LoginStatus.COMPLETE
                    System.err.println(t?.localizedMessage)
                    t?.printStackTrace()
                    errorMessage = "Ocurrió un error"
                    handleLoginError()
                }

                override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                    loginStatus.value = LoginStatus.COMPLETE
                    val res = response?.body()
                    if (response == null || !response.isSuccessful
                            || res == null || res.code != 200) {
                        println("Response not successful. Code: ${response?.code()}. Message: ${response?.message()}")
                        when (res?.code) {
                            401 -> redirectToSignup(user)
                            else -> "Ocurrió un error"
                        }
                        loginStatus.value = LoginStatus.ERROR
                        return
                    }
                    handleLoginSuccess(res.user!!)
                }

            })
        } catch (ex: ApiException) {
            loginStatus.value = LoginStatus.COMPLETE
            errorMessage = "No ha iniciado sesión con Google"
            loginStatus.value = LoginStatus.ERROR
            System.err.println("${ex.localizedMessage} - ${ex.statusCode}")
            ex.printStackTrace()
        }
        loginStatus.value = LoginStatus.COMPLETE
        loginStatus.value = LoginStatus.SUCCESS
    }

    private fun redirectToSignup(user: User) {
        potentialUser.value = user
    }
}