package com.csgames.mixparadise.ingredients

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.csgames.mixparadise.R
import com.csgames.mixparadise.extensions.setImmersiveMode
import com.csgames.mixparadise.model.StackedIngredient
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

    private var recyclerView: RecyclerView? = null

    val sampleJuice = StackedIngredient("#f5a22c", 0.4F, "orange", "Orange", "liquid", "https://s3.amazonaws.com/shared.ws.mirego.com/csgames19/assets/orange@3x.png")

    val sampleJuices = arrayOf(sampleJuice)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.view_ingredients_dialog, container, false)
        recyclerView = ingredients

        return view.also {
            setupDialogView(it)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun setupDialogView(dialogView: View) {
        dialogView.close.setOnClickListener {
            dismiss()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ingredients.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = IngredientAdapter(this.context, sampleJuices)
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