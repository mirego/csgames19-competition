package com.csgames.mixparadise.api

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.csgames.mixparadise.ingredients.Ingredients
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path


interface DrinkService {
    // TODO: get ingredients
    @Headers("Team: POLInux2")
    @GET("https://mirego-csgames19.herokuapp.com/ingredients?key="+ KEY)
    fun listIngredients(@Header("Authorization") authorizationHeader: String): Call<Ingredients>
    // TODO: serve call
}