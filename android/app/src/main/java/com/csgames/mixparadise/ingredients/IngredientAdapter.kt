package com.csgames.mixparadise.ingredients

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.csgames.mixparadise.model.Ingredient

class IngredientAdapter(private val context: Context?, private val myDataset: List<Ingredient>) :
    RecyclerView.Adapter<IngredientViewHolder>() {

    private val inflater: LayoutInflater
            = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        return IngredientViewHolder(inflater, parent, this.context)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredient: Ingredient = myDataset[position]
        holder.bind(ingredient)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}