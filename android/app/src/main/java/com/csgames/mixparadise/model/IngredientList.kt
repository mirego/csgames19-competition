package com.csgames.mixparadise.model

class IngredientList(
    val drinks: List<Drink>,
    val juices: List<Juice>,
    val ingredients: List<Ingredient>
) {

    data class Drink(
        val id: String,
        val label: String,
        val type: String,
        val weight: Double,
        val imageUrl: String
    )

    data class Juice (
        val id: String,
        val label: String,
        val type: String,
        val color: String,
        val opacity: Double,
        val imageUrl: String
    )

    data class Ingredient (
        val id: String,
        val label: String,
        val type: String,
        val weight: Double,
        val imageUrl: String
    )
}