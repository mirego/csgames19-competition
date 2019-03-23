package com.csgames.mixparadise.api.dto

import com.csgames.mixparadise.model.IngredientCategories
import com.csgames.mixparadise.model.IngredientCategory

object IngredientCategoryAssembler {

    fun from(getIngredientsResponse: GetIngredientsResponse) : List<IngredientCategory> {
        return listOf(IngredientCategory(IngredientCategories.ALCOHOL, getIngredientsResponse.alcohols),
            IngredientCategory(IngredientCategories.JUICES, getIngredientsResponse.juices),
            IngredientCategory(IngredientCategories.INGREDIENTS, getIngredientsResponse.ingredients),
            IngredientCategory(IngredientCategories.DRINKS, getIngredientsResponse.drinks))
    }
}