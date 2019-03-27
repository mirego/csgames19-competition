package com.csgames.mixparadise.model

data class IngredientResponse(
    val juices: List<Juice>,
    val drinks: List<Drink>,
    val ingredients: List<Ingredient>,
    val alcohols: List<Alcohol>
)