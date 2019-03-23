package com.csgames.mixparadise.ingredients

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.csgames.mixparadise.R
import com.csgames.mixparadise.model.Ingredient


class IngredientViewHolder(inflater: LayoutInflater, parent: ViewGroup, private val context: Context?) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.view_ingredient_item, parent, false)) {
    private var countView: TextView? = null
    private var titleView: TextView? = null
    private var imageView: ImageView? = null

    init {
        countView = itemView.findViewById(R.id.count)
        titleView = itemView.findViewById(R.id.title)
        imageView = itemView.findViewById(R.id.image_view)
    }

    fun bind(ingredient: Ingredient) {
        countView?.text = "2"
        titleView?.text = ingredient.label
        Glide.with(context!!).load(ingredient.imageUrl).into(imageView!!)
    }
}