package com.csgames.mixparadise.ingredients

import android.os.Bundle
import android.view.*
import android.widget.ListAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.csgames.mixparadise.R
import com.csgames.mixparadise.extensions.setImmersiveMode
import com.csgames.mixparadise.model.IngredientToPicked
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.view_ingredients_dialog.*
import kotlinx.android.synthetic.main.view_ingredients_dialog.view.*

typealias IngredientSelectedListener = (
    id: String
) -> Unit

class IngredientsBottomSheetDialogFragment : BottomSheetDialogFragment() {

    companion object {
        const val INGREDIENTS_ID_TO_OUNCES_MAP_KEY = "INGREDIENTS_ID_TO_OUNCES_MAP_KEY"
    }

    private var ingredientSelectedListener: IngredientSelectedListener? = null
    private val ingredientsList = listOf(
        IngredientToPicked("chat1", 1),
        IngredientToPicked("chat2", 2),
        IngredientToPicked("chat3", 3))

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_ingredients_dialog, container, false).also {
            setupDialogView(it)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun setupDialogView(dialogView: View) {
        // this is called when opening the add ingredients windows

        dialogView.close.setOnClickListener {
            dismiss()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ingredients.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = IngredientAdapter(ingredientsList, ingredientSelectedListener)
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