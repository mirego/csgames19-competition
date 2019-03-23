package com.csgames.mixparadise.model

import com.csgames.mixparadise.Blender

class SolidIngredient(val weight: Double,
                      val sprites: List<String>,
                      override val id: String,
                      override val label: String,
                      override val type: String,
                      override val imageUrl: String) : Ingredient {
    override fun addToBlender(blender: Blender) {
        blender.addSolidIngredient()
    }
}