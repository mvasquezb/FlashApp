package com.oligark.flashapp.service.api

import com.oligark.flashapp.model.Service
import com.oligark.flashapp.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceService {
//    @POST("login")
//    fun loginUser(@Body body: LoginRequest): Call<LoginResponse>

    @GET("services")
    fun findAll(): Call<List<Service>>

//    @GET("users/{id}")
//    fun findById(id: Int): Call<User?>
}