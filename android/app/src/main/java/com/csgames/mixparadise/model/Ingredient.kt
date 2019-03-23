package com.csgames.mixparadise.model

data class Ingredient (
    var id: String = "",
    var label: String = "",
    var type: String = "",
    var weight: Double = 0.0,
    var imageUrl: String = "",
    var sprites: List<String>? = null,
    var count: Int = 0
)