package com.csgames.mixparadise.model

data class Ingredient(
    val id: String,
    val label: String,
    val type: String,
    val weight: Float,
    val imageUrl: String,
    val sprites: List<String>?
)