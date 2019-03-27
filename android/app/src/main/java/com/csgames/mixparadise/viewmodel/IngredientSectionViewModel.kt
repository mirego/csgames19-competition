package com.csgames.mixparadise.viewmodel

import com.csgames.mixparadise.model.Ingredient


class IngredientSectionViewModel(ingredients: List<Ingredient>?, title: String) : BaseSectionViewModel(title) {

    override val sectionType = SectionType.INGREDIENT

    override val ingredients = ingredients?.map {
        IngredientItemViewModel(it.id, it.label, it.imageUrl)
    } ?: emptyList()
}