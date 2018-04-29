package com.oligark.flashapp

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class FlashApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        // TODO: Update for dependency injection
    }
}