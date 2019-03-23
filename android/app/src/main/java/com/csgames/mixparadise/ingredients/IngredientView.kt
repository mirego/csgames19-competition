package com.csgames.mixparadise.ingredients

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.csgames.mixparadise.R
import kotlinx.android.synthetic.main.view_ingredient_item.view.*

class IngredientView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_ingredient_item, this, true)
    }

    fun setupView(ingredient: Ingredient) {
        title.text = ingredient.id
    }
}