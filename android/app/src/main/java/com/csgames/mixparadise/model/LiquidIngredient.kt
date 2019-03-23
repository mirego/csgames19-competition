package com.csgames.mixparadise.model

import com.csgames.mixparadise.Blender

class LiquidIngredient(val color: String,
                       val opacity: Float,
                       override val id: String,
                       override val label: String,
                       override val type: String,
                       override val imageUrl: String
) : Ingredient {

    override fun addToBlender(blender: Blender) {
        blender.addLiquid(id, color, opacity)
    }
}