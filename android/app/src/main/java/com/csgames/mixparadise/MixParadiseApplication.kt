package com.csgames.mixparadise

import android.app.Application
import com.csgames.mixparadise.api.Api
import com.csgames.mixparadise.model.Ingredient
import com.csgames.mixparadise.model.IngredientsResponse

class MixParadiseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Api.init(applicationContext)
    }

    companion object {
        lateinit var ingredients: IngredientsResponse
    }
}