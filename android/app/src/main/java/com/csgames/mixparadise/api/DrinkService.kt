package com.csgames.mixparadise.api

import okhttp3.Cache
import okhttp3.Call
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import java.io.File

// TODO: Write this stuff

interface DrinkService {
    @Headers(
        "Team:friendSHIP"
    )

    @GET("/ingredients")
    fun getIngredients():Call

    @POST("/serve")
    fun serveDrink(ingr: HashMap<String,Int>):Call
}

class Ingr(
    var id: String,
    var qty: Integer
)