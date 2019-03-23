package com.csgames.mixparadise.model

import com.csgames.mixparadise.ingredients.IngredientType

class Liquid(id: String, label: String, val color: String, val opacity: Float, imageUrl: String): Ingredient(id, label, imageUrl, IngredientType.LIQUID)