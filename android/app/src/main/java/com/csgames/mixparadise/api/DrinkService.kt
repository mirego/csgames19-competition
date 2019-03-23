package com.csgames.mixparadise.api

import com.csgames.mixparadise.model.IngredientResponse
import com.csgames.mixparadise.model.SendableIngredient
import com.csgames.mixparadise.model.ServeResponse
import retrofit2.Call
import retrofit2.http.*

interface DrinkService {

    @Headers("Team: Concordia Cruisaders")
    @GET("/ingredients")
    fun listIngredients(): Call<IngredientResponse>

    @Headers("Team: Concordia Cruisaders")
    @GET("/ingredients")
    fun listSecretIngredients(@Query("key") key: String, @Header("Authorization") token: String):
            Call<IngredientResponse>

    @Headers("Team: Concordia Cruisaders")
    @POST("/serve")
    fun serveDrink(@Body ingredients: List<SendableIngredient>): Call<ServeResponse>

}