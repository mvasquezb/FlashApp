package com.oligark.flashapp.service.api

import com.oligark.flashapp.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @POST("login")
    fun loginUser(@Body req: LoginRequest): Call<LoginResponse>

    @POST("login/?allow-register")
    fun loginOrRegister(@Body req: LoginRequest): Call<LoginResponse>

    @GET("users")
    fun findAll(): Call<List<User>>

    @GET("users/{id}")
    fun findById(id: Int): Call<User?>
}