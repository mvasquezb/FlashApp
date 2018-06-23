package com.oligark.flashapp.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.oligark.flashapp.R
import com.oligark.flashapp.di.Dependencies
import com.oligark.flashapp.model.User
import com.oligark.flashapp.service.api.LoginRequest
import com.oligark.flashapp.service.api.LoginRequestPayload
import com.oligark.flashapp.service.api.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
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

    var email = ""
    var password = ""
    var errorMessage: String? = null
    val loginStatus = MutableLiveData<LoginStatus>()
    val currentUser = MutableLiveData<User>()

    init {
        loginStatus.value = LoginStatus.NONE
    }

    fun onLogin(loginType: LoginType) {
        // TODO: Add corresponding logic for login type
        loginStatus.value = LoginStatus.LOADING
        when (loginType) {
            LoginType.EMAIL -> {
                if (email.isEmpty() || password.isEmpty()) {
                    errorMessage = .getString(R.string.email_login_fields_error)
                    loginStatus.value = LoginStatus.COMPLETE
                    loginStatus.value = LoginStatus.ERROR
                    return
                }
                Dependencies.userService.loginUser(LoginRequest(
                        loginType.toString(),
                        LoginRequestPayload(email, password)
                )).enqueue(object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                        loginStatus.value = LoginStatus.COMPLETE
                        println(t?.localizedMessage)
                        t?.printStackTrace()
                        handleLoginError()
                    }

                    override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                        loginStatus.value = LoginStatus.COMPLETE
                        handleLoginSuccess(response)
                    }
                })
            }
            LoginType.FB -> {
                // TODO: FB login
                loginStatus.value = LoginStatus.COMPLETE
                loginStatus.value = LoginStatus.SUCCESS
            }
            LoginType.GOOGLE -> {
                // TODO: Google login
                loginStatus.value = LoginStatus.COMPLETE
                loginStatus.value = LoginStatus.SUCCESS
            }
        }
    }

    private fun handleLoginSuccess(response: Response<LoginResponse>?) {
        val res = response?.body()
        if (response == null || !response.isSuccessful
                || res == null || res.code != 200) {
            println("Response not successful. Code: ${response?.code()}. Message: ${response?.message()}")
            loginStatus.value = LoginStatus.ERROR
            return
        }
        val user = res.user!!
        currentUser.value = user
        loginStatus.value = LoginStatus.SUCCESS
    }

    private fun handleLoginError() {
        loginStatus.value = LoginStatus.ERROR
    }
}