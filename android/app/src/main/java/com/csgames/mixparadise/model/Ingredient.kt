package com.csgames.mixparadise.model

import com.csgames.mixparadise.Blender

interface Ingredient {
    val id: String
    val label: String
    val type: String
    val imageUrl: String

    fun addToBlender(blender: Blender)
}