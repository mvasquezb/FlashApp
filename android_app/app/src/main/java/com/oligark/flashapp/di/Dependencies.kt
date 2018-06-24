package com.oligark.flashapp.di

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.Scope
import com.google.gson.GsonBuilder
import com.oligark.flashapp.R
import com.oligark.flashapp.service.api.BaseApi
import com.oligark.flashapp.service.api.ServiceCategoryService
import com.oligark.flashapp.service.api.ServiceService
import com.oligark.flashapp.service.api.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Dependencies(context: Context) {
    companion object {
        private lateinit var _instance: Dependencies
        fun getInstance(): Dependencies {
            return _instance
        }
        @Synchronized
        fun create(context: Context) {
            _instance = Dependencies(context)
        }
    }

    val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create()

    var retrofit = Retrofit.Builder()
            .baseUrl(BaseApi.apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    var serviceService = retrofit.create(ServiceService::class.java)
    var categoryService = retrofit.create(ServiceCategoryService::class.java)
    val userService = retrofit.create(UserService::class.java)

    private var _googleOptions: GoogleSignInOptions? = null
    fun googleOptions(context: Context): GoogleSignInOptions {
        if (_googleOptions == null) {
            _googleOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestScopes(Scope(Scopes.PLUS_ME))
                    .requestEmail()
                    .requestServerAuthCode(context.getString(R.string.google_server_id))
                    .build()
        }
        return _googleOptions!!
    }

    private var _googleSignInClient: GoogleSignInClient? = null
    fun googleSignInClient(context: Context): GoogleSignInClient {
        if (_googleSignInClient == null) {
            _googleSignInClient = GoogleSignIn.getClient(context, googleOptions(context))
        }
        return _googleSignInClient!!
    }
}