package com.csgames.mixparadise.ingredients

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.csgames.mixparadise.R
import com.csgames.mixparadise.model.Ingredient
import com.csgames.mixparadise.model.IngredientCategories
import com.csgames.mixparadise.model.IngredientCategory

class IngredientCategoriesAdapter(val onIngredientClickedListener: OnIngredientClickedListener) : RecyclerView.Adapter<IngredientCategoriesAdapter.IngredientCategoriesViewHolder>() {

    interface OnIngredientClickedListener {
        fun onIngredientClicked(ingredient: Ingredient)
    }

    var ingredientsCategories : List<IngredientCategory> = arrayListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientCategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_ingredient_category, parent, false)
        return IngredientCategoriesViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: IngredientCategoriesViewHolder, position: Int) {
        holder.bind(ingredientsCategories[position])
    }

    override fun getItemCount(): Int {
        return ingredientsCategories.size
    }

    inner class IngredientCategoriesViewHolder(val view: View, val context: Context) : RecyclerView.ViewHolder(view) {

        var categoryTitleTextView: TextView = view.findViewById(R.id.ingredientCategoryTitle)
        var ingredientsRecyclerView: RecyclerView = view.findViewById(R.id.ingredientsRecyclerView)

        fun bind(ingredientCategory: IngredientCategory) {
            categoryTitleTextView.text = ingredientCategory.category.title
            val adapter = IngredientsAdapter(object : IngredientsAdapter.OnIngredientClickedListener {
                override fun onIngredientClicked(ingredient: Ingredient) {
                    onIngredientClickedListener.onIngredientClicked(ingredient)
                }
            })
            adapter.ingredients = ingredientCategory.ingredients
            ingredientsRecyclerView.layoutManager = GridLayoutManager(context, 4)
            ingredientsRecyclerView.adapter = adapter

        }
    }
}