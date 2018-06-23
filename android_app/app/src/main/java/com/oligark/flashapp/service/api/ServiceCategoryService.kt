package com.oligark.flashapp.service.api

import com.oligark.flashapp.model.ServiceCategory
import retrofit2.Call
import retrofit2.http.GET

interface ServiceCategoryService {

    @GET("services")
    fun findAll(): Call<List<ServiceCategory>>

    @GET("services/{id}")
    fun findAll( id: Int): Call<List<ServiceCategory>>

}