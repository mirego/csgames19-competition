package com.csgames.mixparadise.api

import com.csgames.mixparadise.model.IngredientRequest
import com.csgames.mixparadise.model.IngredientResponse
import com.csgames.mixparadise.model.ServeResponse
import retrofit2.Call
import retrofit2.http.*

interface DrinkService {
    @Headers("Team: Street")
    @GET("/")
    fun listIngredients(@Query("key") key: String, @Header("Authorization") authorization: String): Call<IngredientResponse>

    @Headers("Team: Street")
    @POST("/serve")
    fun serve(@Body ingredients: List<IngredientRequest>): Call<ServeResponse>
}