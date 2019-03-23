package com.csgames.mixparadise.model

class LiquidIngredient(val color: String,
                       val opacity: Double,
                       override val id: String,
                       override val label: String,
                       override val type: String,
                       override val imageUrl: String
) : Ingredient