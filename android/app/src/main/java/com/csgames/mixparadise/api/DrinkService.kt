package com.csgames.mixparadise.api

import com.csgames.mixparadise.model.IngredientsResponse
import com.csgames.mixparadise.model.ServedDrinkResponse
import retrofit2.http.GET
import retrofit2.http.POST

interface DrinkService {
    @GET("/ingredients")
    fun getIngredients(): IngredientsResponse

    @POST("/serve")
    fun serveBeverage(): ServedDrinkResponse
}