package com.csgames.mixparadise.model

data class IngredientsResponse(
    val juices: List<Juice>,
    val drinks: List<Juice>,
    val ingredients: List<Ingredient>,
    val alcohols: List<Juice>
)