package com.csgames.mixparadise.ingredients

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.csgames.mixparadise.R
import com.csgames.mixparadise.api.Api
import com.csgames.mixparadise.api.dto.GetIngredientsResponse
import com.csgames.mixparadise.api.dto.IngredientCategoryAssembler
import com.csgames.mixparadise.extensions.setImmersiveMode
import com.csgames.mixparadise.model.Ingredient
import com.csgames.mixparadise.model.IngredientCategories
import com.csgames.mixparadise.model.IngredientCategory
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.view_ingredients_dialog.*
import kotlinx.android.synthetic.main.view_ingredients_dialog.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

typealias IngredientSelectedListener = (
    ingredient: Ingredient
) -> Unit

class IngredientsBottomSheetDialogFragment : BottomSheetDialogFragment(), IngredientCategoriesAdapter.OnIngredientClickedListener  {

    companion object {
        const val INGREDIENTS_ID_TO_OUNCES_MAP_KEY = "INGREDIENTS_ID_TO_OUNCES_MAP_KEY"
    }

    private var ingredientSelectedListener: IngredientSelectedListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_ingredients_dialog, container, false).also {
            setupDialogView(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getIngredients()
    }

    override fun onIngredientClicked(ingredient: Ingredient) {
        ingredientSelectedListener?.invoke(ingredient)
        dismiss()
    }

    @Suppress("UNCHECKED_CAST")
    private fun setupDialogView(dialogView: View) {
        dialogView.close.setOnClickListener {
            dismiss()
        }

    }

    private fun getIngredients() {
        Api.drinkService.getIngredients().enqueue(object : Callback<GetIngredientsResponse> {
            override fun onResponse(call: Call<GetIngredientsResponse>, response: Response<GetIngredientsResponse>) {
                response.body()?.let { ingredientsResponse ->
                    ingredients_categories_recyclerview?.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    val adapter = IngredientCategoriesAdapter(this@IngredientsBottomSheetDialogFragment)
                    adapter.ingredientsCategories = IngredientCategoryAssembler.from(ingredientsResponse)
                    ingredients_categories_recyclerview?.adapter = adapter
                }
            }

            override fun onFailure(call: Call<GetIngredientsResponse>, t: Throwable) {
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