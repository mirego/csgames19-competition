package com.csgames.mixparadise.ingredients

import android.os.Bundle
import android.view.*
import androidx.recyclerview.widget.GridLayoutManager
import com.csgames.mixparadise.MixParadiseApplication
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.csgames.mixparadise.R
import com.csgames.mixparadise.adapter.JuiceAdapter
import com.csgames.mixparadise.extensions.setImmersiveMode
import com.csgames.mixparadise.model.Juice
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
    lateinit var juiceAdapter: JuiceAdapter
    lateinit var drinkAdapter: JuiceAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_ingredients_dialog, container, false).also {
            setupDialogView(it)

            it.juices.setLayoutManager(GridLayoutManager(activity, 4))
            it.juices.setNestedScrollingEnabled(false);
            juiceAdapter = JuiceAdapter(MixParadiseApplication.ingredients.juices, this!!.context!!, object:
                JuiceAdapter.OnItemClickListener {
                override fun onItemClickListener(item: Juice) {

                }
            })

            it.juices.adapter = juiceAdapter


            it.drinks.setLayoutManager(GridLayoutManager(activity, 4))
            it.drinks.setNestedScrollingEnabled(false);
            drinkAdapter = JuiceAdapter(MixParadiseApplication.ingredients.drinks, this!!.context!!, object:
                JuiceAdapter.OnItemClickListener {
                override fun onItemClickListener(item: Juice) {

                }
            })

            it.drinks.adapter = drinkAdapter
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun setupDialogView(dialogView: View) {
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