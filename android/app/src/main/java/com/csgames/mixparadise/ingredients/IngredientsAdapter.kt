package com.csgames.mixparadise.ingredients

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.csgames.mixparadise.R
import com.csgames.mixparadise.model.IngredientsResponse

// Since the adapter is never changing, there's no need to use ListAdapter
class IngredientsAdapter(private val response: IngredientsResponse) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val headerPositions = List(4) {
        when (it) {
            0 -> 0
            1 -> response.juices.size
            2 -> response.juices.size + response.drinks.size
            3 -> response.juices.size + response.drinks.size + response.ingredients.size
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.view_section_title_item -> IngredientHeaderVH(view)
            else -> IngredientsViewHolder(view)
        }
    }

    override fun onBindViewHolder(holderParent: RecyclerView.ViewHolder, position: Int) {
        // TODO: FIX
        val ingredient = response.juices[position]

        if (holderParent is IngredientHeaderVH) {
            val holder = holderParent as IngredientHeaderVH
            holder.title.text = when (position) {
                headerPositions[0] -> "Juices"
                headerPositions[1] -> "Drinks"
                headerPositions[2] -> "Ingredients"
                else -> "Alcohol"
            }
        } else if (holderParent is IngredientsViewHolder) {
            with(holderParent) {
                title.text = ingredient.label
                Glide
                    .with(imageView)
                    .load(ingredient.imageUrl)
                    .into(imageView)
            }
        }
    }

    override fun getItemCount() = response.alcohols.size + response.drinks.size + response.ingredients.size + response.juices.size + 4

    override fun getItemViewType(position: Int): Int {
        return if (headerPositions.any { it == position }) {
            R.layout.view_section_title_item
        } else {
            R.layout.view_ingredient_item
        }
    }
}