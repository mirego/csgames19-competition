package com.csgames.mixparadise.api

import com.csgames.mixparadise.model.IngredientsResponse
import com.csgames.mixparadise.model.ServeItem
import com.csgames.mixparadise.model.ServeResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DrinkService {
    @GET("ingredients")
    fun getIngredients(@Query("key") key:String = Api.KEY): Call<IngredientsResponse>

    @POST("serve")
    fun serveRecipe(@Body List<ServeItem>) :Call<ServeResponse>
}