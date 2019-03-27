package com.csgames.mixparadise.viewmodel

import com.csgames.mixparadise.model.Juice


class JuiceSectionViewModel(juices: List<Juice>?, title: String) : BaseSectionViewModel(title) {

    override val sectionType = SectionType.JUICE

    override val ingredients = juices?.map {
        IngredientItemViewModel(it.id, it.label, it.imageUrl)
    } ?: emptyList()
}