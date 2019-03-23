package com.csgames.mixparadise.ingredients

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.csgames.mixparadise.R
import com.csgames.mixparadise.model.IngredientCategory

class IngredientCategoriesAdapter : RecyclerView.Adapter<IngredientCategoriesAdapter.IngredientCategoriesViewHolder>() {

    var ingredientsCategories : List<IngredientCategory> = arrayListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientCategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_ingredient_category, parent, false)
        return IngredientCategoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientCategoriesViewHolder, position: Int) {
        holder.bind(ingredientsCategories[position])
    }

    override fun getItemCount(): Int {
        return ingredientsCategories.size
    }

    inner class IngredientCategoriesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        lateinit var categoryTitleTextView: TextView
        lateinit var ingredientsRecyclerView: RecyclerView

        init {
            categoryTitleTextView = view.findViewById(R.id.ingredientCategoryTitle)
            ingredientsRecyclerView = view.findViewById(R.id.ingredientsRecyclerView)
        }

        fun bind(ingredientCategory: IngredientCategory) {
            categoryTitleTextView.text = ingredientCategory.category.title

        }
    }
}