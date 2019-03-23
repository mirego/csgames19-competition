package com.csgames.mixparadise.ingredients

import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelProviders.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.csgames.mixparadise.R
import com.csgames.mixparadise.extensions.setImmersiveMode
import com.csgames.mixparadise.model.IngredientsResponse
import com.csgames.mixparadise.model.Liquid
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.view_ingredients_dialog.view.*

typealias IngredientSelectedListener = (
    id: String
) -> Unit

class IngredientsBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private lateinit var viewModel: IngredientsViewModel

    companion object {
        const val INGREDIENTS_ID_TO_OUNCES_MAP_KEY = "INGREDIENTS_ID_TO_OUNCES_MAP_KEY"
    }

    private var ingredientSelectedListener: IngredientSelectedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*viewModel = ViewModelProviders.of(this)[IngredientsViewModel::class.java]
        viewModel.fetchIngredients()*/
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_ingredients_dialog, container, false).also {
            setupDialogView(it)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun setupDialogView(dialogView: View) {
       /* viewModel.ingredients.observe(this, Observer {
            dialogView.ingredients.adapter = IngredientsAdapter(it)
        })*/
        dialogView.ingredients.adapter = IngredientsAdapter(IngredientsResponse(listOf(Liquid("", "Juice", "#f5a22c", 0.4f, "https://s3.amazonaws.com/shared.ws.mirego.com/csgames19/assets/orange@3x.png")), emptyList(), emptyList(), emptyList()))
        dialogView.close.setOnClickListener {
            dismiss()
        }

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