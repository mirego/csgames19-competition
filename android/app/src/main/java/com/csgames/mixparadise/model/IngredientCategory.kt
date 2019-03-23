package com.csgames.mixparadise.model

class IngredientCategory (val category: IngredientCategories, val ingredients: List<Ingredient>)

enum class IngredientCategories(val title: String) {
    ALCOHOL("Alcohol"),
    DRINKS("Drinks"),
    JUICES("Juices"),
    INGREDIENTS("Ingredients")
}