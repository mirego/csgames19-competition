package com.csgames.mixparadise.viewmodel

abstract class BaseSectionViewModel(val title: String) {
    abstract val sectionType: SectionType

    abstract val ingredients: List<IngredientItemViewModel>
}