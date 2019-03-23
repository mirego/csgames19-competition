package com.csgames.mixparadise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.csgames.mixparadise.api.Api
import com.csgames.mixparadise.extensions.*
import com.csgames.mixparadise.ingredients.IngredientsBottomSheetDialogFragment
import com.csgames.mixparadise.model.IngredientResponse
import com.csgames.mixparadise.model.SendableIngredient
import com.csgames.mixparadise.model.ServeResponse
import kotlinx.android.synthetic.main.view_blender_with_table.*
import com.csgames.mixparadise.result.ResultDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.MessageDigest
import java.util.*


class MainActivity : AppCompatActivity() {

    companion object {
        private const val RESULT_FRAGMENT_TAG = "RESULT_FRAGMENT_TAG"
    }

    private val ingredientsDialog = IngredientsBottomSheetDialogFragment()
    private val resultDialog = ResultDialogFragment()
    private var ingredients: IngredientResponse? = null
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

        val key = java.util.UUID.randomUUID().toString()
        val currentTime = (System.currentTimeMillis() / 1000 / 60).toString()
        val prefix = "csgames19"

        val authorization = hashString("SHA-1", "$prefix-$currentTime-$key").toLowerCase()

        Api.drinkService.listSecretIngredients(key, authorization).enqueue(
            object : Callback<IngredientResponse> {

                override fun onResponse(call: Call<IngredientResponse>?, response: Response<IngredientResponse>) {
                    Log.v("Response success", response.body().toString())
                    ingredients = response.body()
                }

                override fun onFailure(call: Call<IngredientResponse>?, t: Throwable?) {
                    Log.v("Error", t.toString())
                }
            }
        )

        // Example of a serving call


        val ings: List<SendableIngredient> = Arrays.asList(
            SendableIngredient("grapefruit", 1),
            SendableIngredient("cherry", 2),
            SendableIngredient("7up", 1),
            SendableIngredient("tequila", 2),
            SendableIngredient("ice", 2),
            SendableIngredient("mint", 1))

        Api.drinkService.serveDrink(ings).enqueue(
            object : Callback<ServeResponse> {

                override fun onResponse(call: Call<ServeResponse>?, response: Response<ServeResponse>) {
                    Log.v("Response success", response.body().toString())
                }

                override fun onFailure(call: Call<ServeResponse>?, t: Throwable?) {
                    Log.v("Error", t.toString())
                }
            }
        )



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
            fragment.setIngredientSelectedListener { id ->

            }
        }
    }

    // TODO: pass the juice
    private fun onJuiceSelected() {
        blender.addLiquid("orange", "#A66C1E", 0.5f)
    }

    // TODO: pass the drink
    private fun onDrinkSelected() {
        blender.addLiquid("pepsi", "#A66C1E", 0.5f)
    }

    // TODO: pass the ingredient
    private fun onIngredientSelected() {
        blender.addSolidIngredient()
    }

    // TODO: pass the alcohol
    private fun onAlcoholSelected() {
        blender.addLiquid("Four Loko", "#A66C1E", 0.5f)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.setImmersiveMode()
        }
    }

    private fun hashString(type: String, input: String): String {
        val HEX_CHARS = "0123456789ABCDEF"
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
}
