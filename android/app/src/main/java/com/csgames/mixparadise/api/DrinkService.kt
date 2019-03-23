package com.csgames.mixparadise.api

import okhttp3.Call
import retrofit2.http.GET


interface DrinkService {
    // TODO: get ingredients
    @GET("/ingredient")
    fun get_ingredients(): IngredientResponse

    @GET("/serve")
    fun serve_recipe(id: String, qty: Int);
    // TODO: serve call
}

// TODO : move to an other file
object IngredientResponse {
    data class Result(
        val juices: List<Liquid>,
        val drinks: List<Liquid>,
        val ingredients: List<Solid>
    )

    data class Solid(
        val id: String,
        val label: String,
        val type: String = "solid",
        val weight: Double,
        val imageUrl: String,
        val sprites: List<String>
    );

    data class Liquid(
        val id: String,
        val label: String,
        val type: String = "liquid",
        val color: String,
        val opacity: Double,
        val imageUrl: String
    );
}