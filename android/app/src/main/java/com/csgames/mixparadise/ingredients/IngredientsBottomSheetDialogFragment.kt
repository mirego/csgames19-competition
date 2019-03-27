package com.csgames.mixparadise.ingredients

import android.os.Bundle
import android.os.Handler
import android.view.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.csgames.mixparadise.R
import com.csgames.mixparadise.api.ApiHelper
import com.csgames.mixparadise.extensions.setImmersiveMode
import com.csgames.mixparadise.viewmodel.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.view_ingredients_dialog.view.*

typealias IngredientSelectedListener = (
    id: String,
    sectionType: SectionType
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

        val ingredientsIdToOuncesMap = arguments?.getSerializable(INGREDIENTS_ID_TO_OUNCES_MAP_KEY) as? Map<String, Int>
        val ingredientsAdapter = IngredientsAdapter(ingredientsIdToOuncesMap ?: emptyMap()) { id, sectionType ->
            ingredientSelectedListener?.invoke(id, sectionType)
            Handler().postDelayed({
                dismiss()
            }, 500)
        }

        setupRecyclerView(dialogView, ingredientsAdapter)
        populateAdapter(ingredientsAdapter, dialogView)
    }

    fun setIngredientSelectedListener(ingredientSelectedListener: IngredientSelectedListener) {
        this.ingredientSelectedListener = ingredientSelectedListener
    }

    private fun setupRecyclerView(dialogView: View, ingredientsAdapter: IngredientsAdapter) {
        val spanCount = dialogView.context.resources.getInteger(R.integer.ingredients_recycler_view_span_count)
        val layoutManager = GridLayoutManager(dialogView.context, spanCount)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (position != RecyclerView.NO_POSITION && ingredientsAdapter.getItemViewType(position) == IngredientsAdapter.INGREDIENT_ITEM_TYPE) {
                    return 1
                }

                return spanCount
            }
        }
        dialogView.ingredients.layoutManager = layoutManager
        dialogView.ingredients.adapter = ingredientsAdapter

        ingredientsAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                layoutManager.scrollToPosition(0)
            }
        })
    }

    private fun populateAdapter(ingredientsAdapter: IngredientsAdapter, dialogView: View) {
        if (!ApiHelper.hasCache) {
            dialogView.loading.visibility = View.VISIBLE
        }

        ApiHelper.getIngredientResponse { ingredientResponse ->
            ingredientResponse?.let {
                dialogView.loading.visibility = View.GONE

                val sections = listOf(JuiceSectionViewModel(it.juices, getString(R.string.juice_section_title)),
                    DrinkSectionViewModel(it.drinks, getString(R.string.drink_section_title)),
                    IngredientSectionViewModel(it.ingredients, getString(R.string.ingredient_section_title)),
                    AlcoholSectionViewModel(it.alcohols, getString(R.string.alcohol_section_title)))

                ingredientsAdapter.submitSections(sections)
            }
        }
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