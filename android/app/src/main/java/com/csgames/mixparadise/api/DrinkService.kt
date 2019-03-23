package com.csgames.mixparadise.api

import com.csgames.mixparadise.model.IngredientsResponse
import com.csgames.mixparadise.model.ServedDrinkResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST

interface DrinkService {
    @GET("/ingredients")
    fun getIngredients(): Single<IngredientsResponse>

    @POST("/serve")
    fun serveBeverage(): Single<ServedDrinkResponse>
}