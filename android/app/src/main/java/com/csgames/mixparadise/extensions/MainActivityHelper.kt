package com.csgames.mixparadise.extensions

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import com.csgames.mixparadise.Blender
import com.csgames.mixparadise.MainActivity
import com.csgames.mixparadise.ingredients.IngredientsBottomSheetDialogFragment
import kotlinx.android.synthetic.main.view_blender_with_table.*

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
        blender.addLiquid("temp", "#A66C1E", 1f)

        ingredientsDialog.show(supportFragmentManager, ADD_INGREDIENTS_FRAGMENT_TAG)
    }
}

private fun setupView(view: View, parent: View) {
    (view.layoutParams as ViewGroup.MarginLayoutParams).topMargin = (parent.measuredHeight * 0.13).toInt()
    view.layoutParams.height = (parent.measuredHeight * 0.51).toInt()
    view.requestLayout()
}