package com.csgames.mixparadise.ingredients

import com.csgames.mixparadise.viewmodel.IngredientItemViewModel

class IngredientAdapterItemViewModel {

    private var optionalSectionSeparator: SectionSeparatorItemViewModel? = null

    private var optionalSectionTitle: SectionTitleItemViewModel? = null
    val sectionTitle
        get() = optionalSectionTitle

    private var optionalIngredient: IngredientItemViewModel? = null
    val ingredient
        get() = optionalIngredient

    val itemType: IngredientAdapterItemType

    constructor(sectionSeparatorItemViewModel: SectionSeparatorItemViewModel) {
        optionalSectionSeparator = sectionSeparatorItemViewModel
        itemType = IngredientAdapterItemType.SECTION_SEPARATOR
    }

    constructor(sectionTitle: SectionTitleItemViewModel) {
        optionalSectionTitle = sectionTitle
        itemType = IngredientAdapterItemType.SECTION_TITLE
    }

    constructor(ingredient: IngredientItemViewModel) {
        optionalIngredient = ingredient
        itemType = IngredientAdapterItemType.INGREDIENT
    }
}