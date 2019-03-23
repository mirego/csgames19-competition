package com.csgames.mixparadise.model

data class IngredientResponse (
    val juices: List<Ingredient>,
    val drinks: List<Ingredient>,
    val ingredients: List<Ingredient>,
    val alcohols: List<Ingredient>
)