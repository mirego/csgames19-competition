package com.csgames.mixparadise.ingredients

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.csgames.mixparadise.model.IngredientToPicked

class IngredientAdapter(private val ingredientList: List<IngredientToPicked>)
    : RecyclerView.Adapter<IngredientHolder>(), View.OnClickListener  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientHolder {
        val inflater = LayoutInflater.from(parent.context)
        return IngredientHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: IngredientHolder, position: Int) {
        val ingredient: IngredientToPicked = ingredientList[position]
        holder.bind(ingredient)
    }

    override fun getItemCount(): Int = ingredientList.size

    override fun onClick(v: View) {

    }
}