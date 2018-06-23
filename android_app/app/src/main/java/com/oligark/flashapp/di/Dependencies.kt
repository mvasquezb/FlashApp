package com.oligark.flashapp.di

import com.google.gson.GsonBuilder
import com.oligark.flashapp.service.api.BaseApi
import com.oligark.flashapp.service.api.ServiceService
import com.oligark.flashapp.service.api.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Dependencies {
    var gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create()

    var retrofit = Retrofit.Builder()
            .baseUrl(BaseApi.apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    var userService = retrofit.create(UserService::class.java)
    var serviceService = retrofit.create(ServiceService::class.java)
}