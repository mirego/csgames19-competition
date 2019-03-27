package com.csgames.mixparadise.viewmodel

import com.csgames.mixparadise.model.Alcohol


class AlcoholSectionViewModel(alcohols: List<Alcohol>?, title: String) : BaseSectionViewModel(title) {

    override val sectionType = SectionType.ALCOHOL

    override val ingredients = alcohols?.map {
        IngredientItemViewModel(it.id, it.label, it.imageUrl)
    } ?: emptyList()
}