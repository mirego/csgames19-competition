package com.csgames.mixparadise.model.api

import com.csgames.mixparadise.model.Alcohol
import com.csgames.mixparadise.model.Drink
import com.csgames.mixparadise.model.Ingredient
import com.csgames.mixparadise.model.Juice

data class IngredientsResponse(val juices: List<Juice>,
                               val drinks: List<Drink>,
                               val ingredients: List<Ingredient>,
                               val alcohols: List<Alcohol>)