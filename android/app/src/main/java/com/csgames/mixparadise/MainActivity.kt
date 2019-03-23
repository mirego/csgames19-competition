package com.csgames.mixparadise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.csgames.mixparadise.extensions.*
import com.csgames.mixparadise.ingredients.*
import kotlinx.android.synthetic.main.view_blender_with_table.*
import com.csgames.mixparadise.result.ResultDialogFragment


class MainActivity : AppCompatActivity() {

    companion object {
        private const val RESULT_FRAGMENT_TAG = "RESULT_FRAGMENT_TAG"
    }

    private val ingredientsDialog = IngredientsBottomSheetDialogFragment()
    private val resultDialog = ResultDialogFragment()
    private lateinit var blender: Blender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupWaveView()
        setupStackView()
        setupSolidIngredientsWrapper()

        blender = Blender(wave, stackView, solidIngredientsContainer, 10, {
            addIngredientsButton.visibility = View.GONE
            serveButton.visibility = View.VISIBLE
        }) {
            showResultDialog()
        }

        setupListeners(blender, ingredientsDialog)
    }

    private fun showResultDialog() {
        if (resultDialog.isAdded) {
            return
        }

        resultDialog.show(supportFragmentManager, RESULT_FRAGMENT_TAG)
    }

    override fun onAttachFragment(fragment: Fragment?) {
        super.onAttachFragment(fragment)
        (fragment as? IngredientsBottomSheetDialogFragment)?.apply {
            fragment.setIngredientSelectedListener { ingredient ->
                when(ingredient){
                    is Juice -> onJuiceSelected(ingredient)
                    is Drink -> onDrinkSelected(ingredient)
                    is Alcohol -> onAlcoholSelected(ingredient)
                    is Ingredient -> onIngredientSelected(ingredient)
                }
            }
        }
    }

    private fun onJuiceSelected(juice: Juice) {
        blender.addLiquid(juice.id, juice.color, juice.opacity)
    }

    private fun onDrinkSelected(drink: Drink) {
        blender.addLiquid(drink.id, drink.color, drink.opacity)
    }

    private fun onIngredientSelected(ingredient: Ingredient) {
        blender.addSolidIngredient(ingredient)
    }

    private fun onAlcoholSelected(alcohol: Alcohol) {
        blender.addLiquid(alcohol.id, alcohol.color, alcohol.opacity)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.setImmersiveMode()
        }
    }
}
