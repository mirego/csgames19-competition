package com.csgames.mixparadise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.View
import androidx.fragment.app.Fragment
import com.csgames.mixparadise.api.Api
import com.csgames.mixparadise.api.DrinkService
import com.csgames.mixparadise.extensions.*
import com.csgames.mixparadise.ingredients.IngredientCallback
import com.csgames.mixparadise.ingredients.IngredientsBottomSheetDialogFragment
import com.csgames.mixparadise.model.Ingredient
import com.csgames.mixparadise.model.IngredientsResponse
import com.csgames.mixparadise.model.Juice
import kotlinx.android.synthetic.main.view_blender_with_table.*
import com.csgames.mixparadise.result.ResultDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), IngredientCallback {

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

        // test 123
        Api.drinkService.getIngredients().enqueue(object : Callback<IngredientsResponse> {
            override fun onResponse(call: Call<IngredientsResponse>, response: Response<IngredientsResponse>) {
                MixParadiseApplication.ingredients = response.body()!!
                val juices = response.body()!!.juices
                val alcohols = response.body()!!.alcohols
                val drinks = response.body()!!.drinks
            }
            override fun onFailure(call: Call<IngredientsResponse>, t: Throwable) {
                d("ingredients", t.message)
            }

        })
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
            fragment.setIngredientSelectedListener(this@MainActivity)
        }
    }

    override fun onJuiceSelected(juice: Juice) {
        blender.addLiquid(juice.id, juice.color, juice.opacity.toFloat())
    }

    override fun onDrinkSelected(drink: Juice) {
        blender.addLiquid(drink.id, drink.color, drink.opacity.toFloat())
    }

    override fun onIngredientSelected(ingredient: Ingredient) {
        blender.addSolidIngredient()
    }

    override fun onAlcoholSelected(alcohol:Juice) {
        blender.addLiquid(alcohol.id, alcohol.color, alcohol.opacity.toFloat())
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.setImmersiveMode()
        }
    }
}
