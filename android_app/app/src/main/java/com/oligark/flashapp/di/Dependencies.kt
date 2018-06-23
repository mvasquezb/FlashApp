package com.oligark.flashapp.di

import com.google.gson.GsonBuilder
import com.oligark.flashapp.service.api.BaseApi
import com.oligark.flashapp.service.api.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Dependencies {
    val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create()

    val retrofit = Retrofit.Builder()
            .baseUrl(BaseApi.apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val userService = retrofit.create(UserService::class.java)
}