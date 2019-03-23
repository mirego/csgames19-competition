package com.csgames.mixparadise.ingredients

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

typealias ItemClickListener = (basicIngredient: BasicIngredient) -> Unit

class IngredientsAdapter(private val ingredients: ArrayList<BasicIngredient>, private val listener: ItemClickListener) : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    inner class ViewHolder(var ingredientView: View, var context: Context) : RecyclerView.ViewHolder(ingredientView)

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create a new view
        val singleSongView = IngredientView(parent.context)
        singleSongView.layoutParams =
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        // set the view's size, margins, paddings and layout parameters
        val viewHolder = ViewHolder(singleSongView, parent.context)
        singleSongView.setOnClickListener {
            listener(ingredients[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        (holder.ingredientView as IngredientView).setupView(ingredients[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = ingredients.size

}