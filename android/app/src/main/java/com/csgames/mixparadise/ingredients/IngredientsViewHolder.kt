package com.csgames.mixparadise.ingredients

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.csgames.mixparadise.R

class IngredientsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView by lazy { itemView.findViewById<ImageView>(R.id.image_view) }
    val title by lazy { itemView.findViewById<TextView>(R.id.title) }
    val countContainer by lazy { itemView.findViewById<CardView>(R.id.count_container) }
    val count by lazy { itemView.findViewById<TextView>(R.id.count) }
}