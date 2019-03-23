package com.csgames.mixparadise.ingredients

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class Ingredients(
    @SerializedName("juices") val juices: Array<Juice>,
    @SerializedName("ingredients") val ingredients: Array<Ingredient>
)

data class Ingredient(
    @SerializedName("id") val id: String,
    @SerializedName("label") val label: String,
    @SerializedName("type") val type: String,
    @SerializedName("weight") val weight: Float,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("sprites") val sprites: Array<String>
)

data class Juice(
    @SerializedName("id") val id: String,
    @SerializedName("label") val label: String,
    @SerializedName("type") val type: String,
    @SerializedName("color") val color: String,
    @SerializedName("opacity") val opacity: Float,
    @SerializedName("imageUrl") val imageUrl: String
)

