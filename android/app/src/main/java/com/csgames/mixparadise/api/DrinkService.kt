package com.csgames.mixparadise.api

import com.csgames.mixparadise.model.IngredientList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface DrinkService {
    // TODO: get ingredients
    @Headers("Team: xargonauts")
    @GET("ingredients")
    fun getIngredients(): Call<IngredientList>

    // TODO: serve call
}