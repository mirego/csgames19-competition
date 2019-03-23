package com.csgames.mixparadise.api

import com.csgames.mixparadise.model.IngredientsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkService {
    @GET("ingredients")
    fun getIngredients(): Call<IngredientsResponse>


    // TODO: serve call
}