package com.csgames.mixparadise.api

import android.telephony.CellSignalStrength
import retrofit2.Call;
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers


interface DrinkService {

    @GET("/ingredient")
    fun ingredients(): Call<IngredientResponse.Result>

    @GET("/serve")
    fun serve(id: String, qty: Int): Call<ServeResponse.Result>
}

// TODO : move these classes to an other files
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

object ServeResponse {

    data class Result(
        val rating : Rating,
        val review: Review
    )

    data class Rating(
        val note: Int,
        val comment: String
    )

    data class Review(
        val taste: Int,
        val volume: Int,
        val strength: Int
    )
}
