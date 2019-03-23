package com.csgames.mixparadise.ingredients

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

abstract class BasicIngredient{
    abstract var id: String
    abstract var imageUrl: String
}

data class Ingredients(
    @SerializedName("juices") val juices: Array<Juice>,
    @SerializedName("ingredients") val ingredients: Array<Ingredient>,
    @SerializedName("alcohol") val alcohol: Array<Alcohol>
)

data class Ingredient(
    @SerializedName("id") override var id: String,
    @SerializedName("label") val label: String,
    @SerializedName("type") val type: String,
    @SerializedName("weight") val weight: Float,
    @SerializedName("imageUrl") override var imageUrl: String,
    @SerializedName("sprites") val sprites: Array<String>
) : BasicIngredient()

data class Juice(
    @SerializedName("id") override var id: String,
    @SerializedName("label") val label: String,
    @SerializedName("type") val type: String,
    @SerializedName("color") val color: String,
    @SerializedName("opacity") val opacity: Float,
    @SerializedName("imageUrl") override var imageUrl: String
) : BasicIngredient()

data class Alcohol(
    @SerializedName("id") override var id: String,
    @SerializedName("label") val label: String,
    @SerializedName("type") val type: String,
    @SerializedName("color") val color: String,
    @SerializedName("opacity") val opacity: Float,
    @SerializedName("imageUrl") override var imageUrl: String
) : BasicIngredient()