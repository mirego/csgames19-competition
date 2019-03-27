package com.csgames.mixparadise.ingredients

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.doOnLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.csgames.mixparadise.R
import com.csgames.mixparadise.viewmodel.BaseSectionViewModel
import com.csgames.mixparadise.viewmodel.IngredientItemViewModel
import com.csgames.mixparadise.viewmodel.SectionType
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_ingredient_item.*
import kotlinx.android.synthetic.main.view_ingredient_item.view.*
import java.lang.IllegalStateException

typealias IngredientTappedListener = (
    id: String,
    sectionType: SectionType
) -> Unit

class IngredientsAdapter(
    private val ingredientsIdToOunces: Map<String, Int>,
    private val ingredientTappedListener: IngredientTappedListener
) :
    ListAdapter<IngredientAdapterItemViewModel, RecyclerView.ViewHolder>(IngredientItemCallback()) {

    companion object {
        private const val SECTION_SEPARATOR_ITEM_TYPE = 0
        private const val SECTION_TITLE_ITEM_TYPE = 1
        const val INGREDIENT_ITEM_TYPE = 2

        private val itemViewTypeMap = mapOf(
            IngredientAdapterItemType.SECTION_SEPARATOR to SECTION_SEPARATOR_ITEM_TYPE,
            IngredientAdapterItemType.SECTION_TITLE to SECTION_TITLE_ITEM_TYPE,
            IngredientAdapterItemType.INGREDIENT to INGREDIENT_ITEM_TYPE
        )
    }

    private val sections = mutableListOf<BaseSectionViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
        SECTION_SEPARATOR_ITEM_TYPE -> createSectionSeparatorViewHolder(parent)
        SECTION_TITLE_ITEM_TYPE -> createSectionTitleViewHolder(parent)
        INGREDIENT_ITEM_TYPE -> createIngredientViewHolder(parent)
        else -> throw IllegalStateException("Unknown view type")
    }

    private fun createSectionSeparatorViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_section_separator_item, parent, false)
        return SectionSeparatorViewHolder(itemView)
    }

    private fun createSectionTitleViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_section_title_item, parent, false)
        return SectionTitleViewHolder(itemView)
    }

    private fun createIngredientViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_ingredient_item, parent, false)
        val viewHolder = IngredientViewHolder(itemView)

        itemView.card_view.doOnLayout {
            (it as CardView).radius = it.measuredWidth / 2f
        }
        itemView.card_view.setOnClickListener {
            getItem(viewHolder.adapterPosition).ingredient?.let { ingredientItemViewModel ->
                ingredientTappedListener(ingredientItemViewModel.id, sections.first { baseSectionViewModel ->
                    baseSectionViewModel.ingredients.any { it.id == ingredientItemViewModel.id }
                }.sectionType)
            }
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.adapterPosition != RecyclerView.NO_POSITION) {
            val itemViewModel = getItem(holder.adapterPosition)
            when (getItemViewType(holder.adapterPosition)) {
                SECTION_TITLE_ITEM_TYPE -> {
                    itemViewModel.sectionTitle?.let {
                        (holder as SectionTitleViewHolder).bind(it)
                    }
                }
                INGREDIENT_ITEM_TYPE -> {
                    itemViewModel.ingredient?.let {
                        (holder as IngredientViewHolder).bind(it)
                    }
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position != RecyclerView.NO_POSITION) {
            return itemViewTypeMap[getItem(position).itemType] ?: RecyclerView.INVALID_TYPE
        }

        return RecyclerView.INVALID_TYPE
    }

    inner class SectionSeparatorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class SectionTitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(itemViewModel: SectionTitleItemViewModel) {
            (itemView as TextView).text = itemViewModel.title
        }
    }

    inner class IngredientViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(itemViewModel: IngredientItemViewModel) {
            title.text = itemViewModel.title

            Glide.with(containerView.context)
                .load(itemViewModel.imageUrl)
                .into(image_view)

            val ingredientOunces = ingredientsIdToOunces[itemViewModel.id]
            count_container.visibility = if (ingredientOunces != null) View.VISIBLE else View.GONE
            count.text = ingredientOunces?.toString()
        }
    }

    private class IngredientItemCallback : DiffUtil.ItemCallback<IngredientAdapterItemViewModel>() {
        override fun areItemsTheSame(
            oldItem: IngredientAdapterItemViewModel,
            newItem: IngredientAdapterItemViewModel
        ): Boolean {
            if (oldItem.itemType == IngredientAdapterItemType.INGREDIENT && newItem.itemType == IngredientAdapterItemType.INGREDIENT) {
                return oldItem.ingredient?.id == newItem.ingredient?.id
            } else if (oldItem.itemType == IngredientAdapterItemType.SECTION_TITLE && newItem.itemType == IngredientAdapterItemType.SECTION_TITLE) {
                return oldItem.sectionTitle?.title == newItem.sectionTitle?.title
            }

            return false
        }

        override fun areContentsTheSame(
            oldItem: IngredientAdapterItemViewModel,
            newItem: IngredientAdapterItemViewModel
        ): Boolean {
            if (oldItem.itemType == IngredientAdapterItemType.INGREDIENT && newItem.itemType == IngredientAdapterItemType.INGREDIENT) {
                return oldItem.ingredient == newItem.ingredient
            } else if (oldItem.itemType == IngredientAdapterItemType.SECTION_TITLE && newItem.itemType == IngredientAdapterItemType.SECTION_TITLE) {
                return oldItem.sectionTitle == newItem.sectionTitle
            }

            return false
        }
    }

    fun submitSections(sections: List<BaseSectionViewModel>) {
        this.sections.clear()
        this.sections.addAll(sections)

        val ingredientAdapterItems = mutableListOf<IngredientAdapterItemViewModel>()

        sections.forEachIndexed { index, baseSectionViewModel ->
            if (index > 0) {
                ingredientAdapterItems.add(IngredientAdapterItemViewModel(SectionSeparatorItemViewModel()))
            }

            val sectionTitleItemViewModel = SectionTitleItemViewModel(baseSectionViewModel.title)
            ingredientAdapterItems.add(IngredientAdapterItemViewModel(sectionTitleItemViewModel))

            baseSectionViewModel.ingredients.forEach { ingredient ->
                ingredientAdapterItems.add(IngredientAdapterItemViewModel(ingredient))
            }
        }

        submitList(ingredientAdapterItems)
    }
}