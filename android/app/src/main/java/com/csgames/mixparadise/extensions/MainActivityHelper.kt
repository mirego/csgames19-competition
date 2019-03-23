package com.csgames.mixparadise.extensions

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.lifecycle.MutableLiveData
import com.csgames.mixparadise.Blender
import com.csgames.mixparadise.MainActivity
import com.csgames.mixparadise.api.Api.drinkService
import com.csgames.mixparadise.ingredients.IngredientsBottomSheetDialogFragment
import com.csgames.mixparadise.model.IngredientList
import kotlinx.android.synthetic.main.view_blender_with_table.*
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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


        // You know nothing Jon Snow! ¯\_(ツ)_/¯



        val data = MutableLiveData<IngredientList>()

        doAsync {
            drinkService.getIngredients().enqueue(object : Callback<IngredientList> {
                override fun onFailure(call: Call<IngredientList>, t: Throwable) {
                    Log.v("retrofit", "call failed")
                }

                override fun onResponse(call: Call<IngredientList>?, response: Response<IngredientList>?) {
                    data.value = response?.body()

                    val juices = data.value?.juices

                    if (juices != null) {
                        for (j in juices) {
                            System.out.println(j)
                        }
                    }

                }
            })
        }

        // When you play the game of thrones, you win or you die. — Cersei
        // When you play the Cs Games, you win or die. - MP

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

private fun getIngredientList() {

}