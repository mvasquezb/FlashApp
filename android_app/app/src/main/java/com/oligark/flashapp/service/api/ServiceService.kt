package com.oligark.flashapp.service.api

import com.oligark.flashapp.model.Service
import com.oligark.flashapp.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceService {

    @GET("services")
    fun findAll(): Call<List<Service>>

    @GET("services/{id}")
    fun findAll( id: Int): Call<List<Service>>

}