package com.csgames.mixparadise

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.csgames.mixparadise.api.Api
import com.csgames.mixparadise.api.Model
import com.csgames.mixparadise.extensions.*
import com.csgames.mixparadise.ingredients.IngredientsBottomSheetDialogFragment
import kotlinx.android.synthetic.main.view_blender_with_table.*
import com.csgames.mixparadise.result.ResultDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

public val randomKeyString = (1..32)
    .map { i -> kotlin.random.Random.nextInt(0, charPool.size) }
    .map(charPool::get)
    .joinToString("");


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

        val call = Api.drinkService.getIngredients()

        call.enqueue(object : Callback<Model.Result> {
            override fun onFailure(call: Call<Model.Result>?, t: Throwable?) {
                t?.printStackTrace()
            }

            override fun onResponse(call: Call<Model.Result>?, response: Response<Model.Result>?) {
                // Initialize a new instance of
                val builder = AlertDialog.Builder(this@MainActivity)

                // Set the alert dialog title
                builder.setTitle("Waza")

                // Display a message on alert dialog
                builder.setMessage(response?.body()?.alcohols?.size.toString())

                // Finally, make the alert dialog using builder
                val dialog: AlertDialog = builder.create()

                // Display the alert dialog on app interface
                dialog.show()
            }
        })
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
}
