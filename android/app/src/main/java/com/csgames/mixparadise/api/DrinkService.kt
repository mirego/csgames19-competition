package com.csgames.mixparadise.api

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.csgames.mixparadise.ingredients.Ingredients
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


interface DrinkService {
    // TODO: get ingredients
    @Headers("Team: POLInux2", "Authorization: b653fbb50d526643a87c7fca8f29ce0f2b281031")
    @GET("https://mirego-csgames19.herokuapp.com/ingredients")
    fun listIngredients(): Call<Ingredients>
    // TODO: serve call
}