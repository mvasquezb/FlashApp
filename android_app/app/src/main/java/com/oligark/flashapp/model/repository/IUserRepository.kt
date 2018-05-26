package com.oligark.flashapp.model.repository

import android.arch.lifecycle.LiveData
import com.oligark.flashapp.model.User
import com.oligark.flashapp.service.api.LoginRequestPayload

interface IUserRepository {
    // TODO: Should probably be LiveData
    fun loginUser(method: String, request: LoginRequestPayload): LiveData<User?>
    fun findAll(): LiveData<List<User>>
    fun findById(id: Int): LiveData<User?>
}