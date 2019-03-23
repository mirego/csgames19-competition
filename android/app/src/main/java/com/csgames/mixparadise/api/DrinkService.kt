package com.csgames.mixparadise.api

import android.graphics.ColorSpace
import com.csgames.mixparadise.randomKeyString
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface DrinkService {
    @GET("/ingredients?key=tv8PDnId7ylIwGEQ5naooq3wnL205RNR")
    fun getIngredients(): Call<Model.Result>

    // TODO: serve call
}

object Model {
    data class Result(val juices: Array<Liquid>,
                      val drinks: Array<Liquid>,
                      val ingredients: Array<Solid>,
                      val alcohols: Array<Liquid>)

    data class Solid(val id: String, val label: String,
                     val type: String, val weight: Double,
                     val imageUrl: String, val sprites: Array<String>)

    data class Liquid(val id: String, val label: String,
                      val type: String, val color: String,
                      val opacity: Double, val imageUrl: String)
}