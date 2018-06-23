package com.oligark.flashapp.service.api

import com.oligark.flashapp.model.User

data class LoginRequestPayload(
        var email: String? = null,
        var password: String? = null,
        var token: String? = null
)

data class LoginRequest(
        var method: String,
        var payload: LoginRequestPayload
)

data class LoginResponse(
        var code: Int,
        var message: String? = null,
        var user: User? = null
)