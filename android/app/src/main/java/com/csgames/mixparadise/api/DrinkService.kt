package com.csgames.mixparadise.api

import okhttp3.Cache
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
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
    fun getIngredients(@Body request: RequestBody):Call

    @POST("/serve")
    fun serveDrink(ingr: HashMap<String,Int>):Call
}

data class Ingr(
    var id: String,
    var qty: Integer
)

data class DrinkResponse(
    var rating: Rating,
    var review: Review
)

data class Rating(
    var note: Integer,
    var comment: String
)

data class Review(
    var taste: Integer,
    var volume: Integer,
    var strength: Integer
)
