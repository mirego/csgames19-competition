package com.csgames.mixparadise.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface DrinkService {
    //TODO remove key from here

    @GET("ingredients?key=asdasdsdsfds")
    fun getIngredients(): Call<IngredientsReturnModel>

    @GET("serve?key=asdasdsdsfds")
    fun serve(@Query("id") id: String, @Query("qty") quantity: Int) : ServingReview
}

// result generated from /json

data class IngredientsReturnModel(val juices: List<Juice>, val drinks: List<Drink>, val ingredients: List<Ingredient>, val alcohols: List<Alcohol>)

data class Drink(val id: String, val label: String, val type: String, val color: String, val opacity: Number, val imageUrl: String)

data class Ingredient(val id: String, val label: String, val type: String, val weight: Number, val imageUrl: String, val sprites: List<String>)

data class Juice(val id: String, val label: String, val type: String, val color: String, val opacity: Number, val imageUrl: String)

data class Alcohol(val id: String, val label: String, val type: String, val color: String, val opacity: Number, val imageUrl: String, val flag: String)


data class ServingReview(val rating: Rating, val review: Review)

data class Rating(val note: Integer, val comment: String)
data class Review(val taste: Int, val volume: Int, val strength: Int)
