package com.csgames.mixparadise.ingredients

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.csgames.mixparadise.R
import com.csgames.mixparadise.model.IngredientToPicked

class IngredientHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.view_ingredient_item, parent, false)) {
    private var name: TextView? = null
    private var description: TextView? = null


    init {
        name = itemView.findViewById(R.id.title)
        description = itemView.findViewById(R.id.count)
    }

    fun bind(ingredientToPicked: IngredientToPicked) {
        name?.text = ingredientToPicked.name
        description?.text = ingredientToPicked.description
    }

}