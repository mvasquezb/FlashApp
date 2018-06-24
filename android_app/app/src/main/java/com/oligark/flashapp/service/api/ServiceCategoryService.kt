package com.oligark.flashapp.service.api

import com.oligark.flashapp.model.ServiceCategory
import retrofit2.Call
import retrofit2.http.GET

interface ServiceCategoryService {

    @GET("service-types")
    fun findAll(): Call<List<ServiceCategory>>

}