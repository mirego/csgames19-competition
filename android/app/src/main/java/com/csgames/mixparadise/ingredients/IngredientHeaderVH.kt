package com.csgames.mixparadise.ingredients

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.csgames.mixparadise.R

class IngredientHeaderVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title by lazy { itemView.findViewById<TextView>(R.id.title) }
}