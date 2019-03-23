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
        fun resetCounts() {
            for(alcohol in ingredients.alcohols) {
                alcohol.count = 0
            }

            for(juice in ingredients.juices) {
                juice.count = 0
            }

            for(ingredient in ingredients.ingredients) {
                ingredient.count = 0
            }

            for(drink in ingredients.drinks) {
                drink.count = 0
            }
        }
    }
}