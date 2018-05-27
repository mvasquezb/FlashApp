package com.oligark.flashapp.model.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.oligark.flashapp.model.User
import com.oligark.flashapp.service.api.LoginRequest
import com.oligark.flashapp.service.api.LoginRequestPayload
import com.oligark.flashapp.service.api.LoginResponse
import com.oligark.flashapp.service.api.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository : IUserRepository {
    val userService: UserService? = null

    override fun loginUser(method: String, request: LoginRequestPayload): LiveData<User?> {
        val user = MutableLiveData<User?>()
        val req = LoginRequest(method, request)
        userService?.let{
            it.loginUser(req).enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                    // Log error
                    user.value = null
                }

                override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                    val res = response?.body()
                    res?.let {
                        when (res.code) {
                            200 -> user.value = res.user!!
                            400, 404 -> {
                                user.value = null
                                // Log error
                            }
                        }
                    }
                }

            })
        }
        return user
    }

    override fun findAll(): LiveData<List<User>> {
        val userList = MutableLiveData<List<User>>()
        userService?.let {
            it.findAll().enqueue(object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>?, t: Throwable?) {
                    userList.value = listOf()
                }

                override fun onResponse(call: Call<List<User>>?, response: Response<List<User>>?) {
                    userList.value = response?.body()
                }

            })
        }
        return userList
    }

    override fun findById(id: Int): LiveData<User?> {
        val user = MutableLiveData<User?>()
        userService?.let {
            it.findById(id).enqueue(object : Callback<User?> {
                override fun onResponse(call: Call<User?>?, response: Response<User?>?) {
                    user.value = response?.body()
                }

                override fun onFailure(call: Call<User?>?, t: Throwable?) {
                    user.value = null
                }

            })
        }
        return user
    }
}