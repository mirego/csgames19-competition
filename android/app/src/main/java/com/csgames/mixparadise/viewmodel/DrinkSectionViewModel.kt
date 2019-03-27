package com.csgames.mixparadise.viewmodel

import com.csgames.mixparadise.model.Drink


class DrinkSectionViewModel(drinks: List<Drink>?, title: String) : BaseSectionViewModel(title) {

    override val sectionType = SectionType.DRINK

    override val ingredients = drinks?.map {
        IngredientItemViewModel(it.id, it.label, it.imageUrl)
    } ?: emptyList()
}