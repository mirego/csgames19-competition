package com.csgames.mixparadise.extensions

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import com.csgames.mixparadise.Blender
import com.csgames.mixparadise.MainActivity
import com.csgames.mixparadise.ingredients.IngredientsBottomSheetDialogFragment
import com.csgames.mixparadise.model.api.ServedIngredient
import kotlinx.android.synthetic.main.view_blender_with_table.*
import java.security.MessageDigest

private const val ADD_INGREDIENTS_FRAGMENT_TAG = "ADD_INGREDIENTS_FRAGMENT_TAG"

fun MainActivity.setupWaveView() {
    waveContainer.doOnLayout {
        setupView(wave, it)
    }
}

fun MainActivity.setupStackView() {
    waveContainer.doOnLayout {
        setupView(stackView, it)
    }
}

fun MainActivity.setupSolidIngredientsWrapper() {
    waveContainer.doOnLayout {
        setupView(solidIngredientsWrapper, it)
    }
}

fun MainActivity.setupListeners(blender: Blender, ingredientsDialog: IngredientsBottomSheetDialogFragment) {
    blenderView.setOnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_DOWN) {
            blender.start()
        } else if (event.action == MotionEvent.ACTION_UP) {
            blender.stop()
        }

        true
    }

    serveButton.setOnClickListener {
        blender.empty()
        addIngredientsButton.visibility = View.VISIBLE
        serveButton.visibility = View.GONE
    }

    addIngredientsButton.setOnClickListener {
        if (ingredientsDialog.isAdded) {
            return@setOnClickListener
        }

        ingredientsDialog.arguments = Bundle().apply {
            putSerializable(
                IngredientsBottomSheetDialogFragment.INGREDIENTS_ID_TO_OUNCES_MAP_KEY,
                blender.ingredientsIdToOunces
            )
        }

        // TODO: remove me
        blender.addLiquid("temp", "#FFBC31", 1f)

        ingredientsDialog.show(supportFragmentManager, ADD_INGREDIENTS_FRAGMENT_TAG)
    }
}

fun getAuthString(key: String) : String {
    val s = "csgames19-" + (System.currentTimeMillis() / 60000).toInt().toString() + "-" + key
    return hashString("SHA-1", s)
}

fun createPerfectDrink(): List<ServedIngredient> {
    val drink = mutableListOf<ServedIngredient>()
    drink.add(ServedIngredient("rum", 2))
    drink.add(ServedIngredient("coke", 4))
    drink.add(ServedIngredient("mint", 3))

    return drink.toList()
}

private fun setupView(view: View, parent: View) {
    (view.layoutParams as ViewGroup.MarginLayoutParams).topMargin = (parent.measuredHeight * 0.13).toInt()
    view.layoutParams.height = (parent.measuredHeight * 0.51).toInt()
    view.requestLayout()
}

private fun hashString(type: String, input: String): String {
    val HEX_CHARS = "0123456789abcdef"
    val bytes = MessageDigest
        .getInstance(type)
        .digest(input.toByteArray())
    val result = StringBuilder(bytes.size * 2)

    bytes.forEach {
        val i = it.toInt()
        result.append(HEX_CHARS[i shr 4 and 0x0f])
        result.append(HEX_CHARS[i and 0x0f])
    }

    return result.toString()
}