package com.csgames.mixparadise.ingredients

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.csgames.mixparadise.R
import com.csgames.mixparadise.model.Ingredient

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {

    var ingredients: List<Ingredient> = arrayListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_ingredient_item, parent, false)
        return IngredientsViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    inner class IngredientsViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        var imageView : ImageView = view.findViewById(R.id.image_view)
        var titleTextView : TextView = view.findViewById(R.id.title)
        var countTextView: TextView = view.findViewById(R.id.count)

        fun bind(ingredient: Ingredient) {
            Glide.with(view).load(Uri.parse(ingredient.imageUrl)).into(imageView)
            titleTextView.text = ingredient.label
        }
    }


}