package com.oligark.flashapp

import android.app.Application
import com.oligark.flashapp.di.Dependencies

class FlashApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Dependencies.create(this)
    }
}