package com.csgames.mixparadise.ingredients

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.csgames.mixparadise.R
import com.csgames.mixparadise.model.IngredientToPicked

class IngredientHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.view_ingredient_item, parent, false))
    , View.OnClickListener {
    private var name: TextView? = null
    private var count: TextView? = null
    private var ingredientSelectedListener: IngredientSelectedListener? = null


    init {
        name = itemView.findViewById(R.id.title)
        count = itemView.findViewById(R.id.count)
    }

    fun bind(ingredientToPicked: IngredientToPicked, ingredientSelectedListener: IngredientSelectedListener?) {
        this.ingredientSelectedListener = ingredientSelectedListener
        name?.text = ingredientToPicked.name
        count?.text = ingredientToPicked.count.toString()
    }

    override fun onClick(v: View) {
        // this should have a id and use the id instead of the count
        ingredientSelectedListener?.invoke(count?.text.toString())
    }

}