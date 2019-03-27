package com.csgames.mixparadise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.csgames.mixparadise.api.ApiHelper
import com.csgames.mixparadise.extensions.*
import com.csgames.mixparadise.ingredients.IngredientsBottomSheetDialogFragment
import com.csgames.mixparadise.model.Alcohol
import com.csgames.mixparadise.model.Drink
import com.csgames.mixparadise.model.Ingredient
import com.csgames.mixparadise.model.Juice
import com.csgames.mixparadise.viewmodel.SectionType
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
            showResultDialog(it)
        }

        setupListeners(blender, ingredientsDialog)
    }

    private fun showResultDialog(ingredientsIdToOuncesMap: HashMap<String, Int>) {
        if (resultDialog.isAdded) {
            return
        }
        resultDialog.arguments = Bundle().apply {
            putSerializable(
                ResultDialogFragment.INGREDIENTS_ID_TO_OUNCES_MAP_KEY,
                ingredientsIdToOuncesMap
            )
        }
        resultDialog.show(supportFragmentManager, RESULT_FRAGMENT_TAG)
    }

    override fun onAttachFragment(fragment: Fragment?) {
        super.onAttachFragment(fragment)
        (fragment as? IngredientsBottomSheetDialogFragment)?.apply {
            fragment.setIngredientSelectedListener { id, sectionType ->
                getIngredient(id, sectionType)
            }
        }
    }

    private fun getIngredient(id: String, sectionType: SectionType) {
        when (sectionType) {
            SectionType.JUICE -> ApiHelper.getJuice(id) { juice ->
                juice?.let {
                    onJuiceSelected(it)
                }
            }
            SectionType.DRINK -> ApiHelper.getDrink(id) { drink ->
                drink?.let {
                    onDrinkSelected(it)
                }
            }
            SectionType.INGREDIENT -> ApiHelper.getIngredient(id) { ingredient ->
                ingredient?.let {
                    onIngredientSelected(it)
                }
            }
            SectionType.ALCOHOL -> ApiHelper.getAlcohol(id) { alcohol ->
                alcohol?.let {
                    onAlcoholSelected(it)
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
