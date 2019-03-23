package com.csgames.mixparadise.model

import com.csgames.mixparadise.ingredients.IngredientType

data class Solid(val id: String, val label: String, val type: IngredientType, val color: String, val opacity: Float, val imageUrl: String)