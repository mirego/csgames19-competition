package com.csgames.mixparadise

import android.app.Application
import android.text.format.Time
import com.csgames.mixparadise.api.Api
import java.text.DateFormat
import java.time.ZoneOffset
import java.util.*

class MixParadiseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Api.init(applicationContext)
        Api.setAuthRequest(applicationContext)
    }
}