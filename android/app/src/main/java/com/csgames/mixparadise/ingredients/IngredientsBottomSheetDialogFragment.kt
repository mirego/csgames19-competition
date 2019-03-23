package com.csgames.mixparadise.ingredients

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.csgames.mixparadise.R
import com.csgames.mixparadise.api.Api
import com.csgames.mixparadise.extensions.setImmersiveMode
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.view_ingredients_dialog.*
import kotlinx.android.synthetic.main.view_ingredients_dialog.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

typealias IngredientSelectedListener = (
    id: String
) -> Unit

class IngredientsBottomSheetDialogFragment : BottomSheetDialogFragment() {

    companion object {
        const val INGREDIENTS_ID_TO_OUNCES_MAP_KEY = "INGREDIENTS_ID_TO_OUNCES_MAP_KEY"
    }

    private var ingredientSelectedListener: IngredientSelectedListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_ingredients_dialog, container, false).also {
            setupDialogView(it)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun setupDialogView(dialogView: View) {
        dialogView.close.setOnClickListener {
            dismiss()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Api.drinkService.listIngredients(Api.createHeader()).enqueue(object : Callback<Ingredients> {
            override fun onFailure(call: Call<Ingredients>, t: Throwable) {
                Log.e("FAILLLLLLLL", t.message)
            }

            override fun onResponse(call: Call<Ingredients>, response: Response<Ingredients>) {
                ingredients.adapter = IngredientsAdapter(response.body()!!.ingredients.toCollection(ArrayList()))
                // Set layout manager to position the items
                ingredients.layoutManager = LinearLayoutManager(context)
                Log.e("HELLLOOOOOO", response.body()!!.ingredients[0].id)
            }
        })
    }

    fun setIngredientSelectedListener(ingredientSelectedListener: IngredientSelectedListener) {
        this.ingredientSelectedListener = ingredientSelectedListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) = super.onCreateDialog(savedInstanceState).also {
        it.window?.setImmersiveMode()
    }

    override fun onStart() {
        super.onStart()

        dialog?.findViewById<View>(R.id.design_bottom_sheet)?.let { bottomSheet ->
            BottomSheetBehavior.from(bottomSheet).isHideable = false
            bottomSheet.layoutParams?.height = ViewGroup.LayoutParams.MATCH_PARENT
        }
    }
}