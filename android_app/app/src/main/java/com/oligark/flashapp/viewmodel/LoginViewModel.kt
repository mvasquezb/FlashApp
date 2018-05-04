package com.oligark.flashapp.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

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
    val loginStatus = MutableLiveData<LoginStatus>()

    init {
        loginStatus.value = LoginStatus.NONE
    }

    fun onLogin(loginType: LoginType) {
        // TODO: Add corresponding logic for login type
        loginStatus.value = LoginStatus.LOADING
        when (loginType) {
            LoginType.EMAIL -> {
                loginStatus.value = LoginStatus.COMPLETE
                loginStatus.value = LoginStatus.SUCCESS
            }
            LoginType.FB -> {
                loginStatus.value = LoginStatus.COMPLETE
                loginStatus.value = LoginStatus.SUCCESS
            }
            LoginType.GOOGLE -> {
                loginStatus.value = LoginStatus.COMPLETE
                loginStatus.value = LoginStatus.SUCCESS
            }
        }
    }
}