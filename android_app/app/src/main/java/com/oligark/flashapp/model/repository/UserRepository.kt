package com.oligark.flashapp.model.repository

import android.arch.lifecycle.LiveData
import com.oligark.flashapp.model.User
import com.oligark.flashapp.service.api.LoginRequestPayload

class UserRepository : IUserRepository {
    override fun loginUser(method: String, request: LoginRequestPayload): LiveData<User?> {

    }

    override fun findAll(): LiveData<List<User>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findById(id: Int): LiveData<User?> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}