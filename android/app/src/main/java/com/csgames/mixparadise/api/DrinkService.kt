package com.csgames.mixparadise.api

import com.csgames.mixparadise.api.dto.GetIngredientsResponse
import com.csgames.mixparadise.api.dto.PostServeRequest
import com.csgames.mixparadise.api.dto.PostServeResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DrinkService {
    @GET("/ingredients")
    fun getIngredients() : Call<GetIngredientsResponse>

    @POST("/serve")
    fun serve(@Body serveRequest: PostServeRequest) : Call<PostServeResponse>
}