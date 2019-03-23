package com.csgames.mixparadise.ingredients

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.csgames.mixparadise.R
import kotlinx.android.synthetic.main.view_ingredient_item.view.*

class IngredientView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_ingredient_item, this, true)
    }

    fun setupView(ingredient: BasicIngredient) {
        title.text = ingredient.id
        Glide.with(context)
            .load(ingredient.imageUrl)
            .into(image_view)
    }
}