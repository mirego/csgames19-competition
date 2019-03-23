package com.csgames.mixparadise.api

import com.csgames.mixparadise.model.IngredentEndPointRes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers


interface DrinkService {
    // TODO: get ingredients
    @Headers("Team: McGill")
    @GET("/ingredients")
    fun getIngredients(): Call<IngredentEndPointRes>


   @Headers("Team: McGill")
   @GET("/ingredients")
   fun getSecretIngredient():  Call<IngredentEndPointRes>


    // TODO: serve call
}