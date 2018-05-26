package com.oligark.flashapp.service.api

import com.oligark.flashapp.model.User

data class LoginRequestPayload(
        val email: String? = null,
        val password: String? = null,
        val token: String? = null
)

data class LoginRequest(
        val method: String,
        val payload: LoginRequestPayload
)

data class LoginResponse(
        val code: Int,
        val message: String,
        val user: User? = null
)