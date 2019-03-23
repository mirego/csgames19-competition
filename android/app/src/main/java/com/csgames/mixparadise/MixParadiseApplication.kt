package com.csgames.mixparadise

import android.app.Application
import com.csgames.mixparadise.api.Api

class MixParadiseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Api.init(applicationContext)
    }
}