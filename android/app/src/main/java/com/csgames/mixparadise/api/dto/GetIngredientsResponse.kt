package com.csgames.mixparadise.api.dto

import com.csgames.mixparadise.model.LiquidIngredient
import com.csgames.mixparadise.model.SolidIngredient

data class GetIngredientsResponse(val juices: List<LiquidIngredient>,
                                  val drinks: List<LiquidIngredient>,
                                  val ingredients: List<SolidIngredient>,
                                  val alcohols: List<LiquidIngredient>)