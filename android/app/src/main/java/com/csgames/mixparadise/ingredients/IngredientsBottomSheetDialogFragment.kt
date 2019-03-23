package com.csgames.mixparadise.ingredients

import android.app.AlertDialog
import android.os.Bundle
import android.system.Os.open
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.csgames.mixparadise.R
import com.csgames.mixparadise.api.Api
import com.csgames.mixparadise.api.Model
import com.csgames.mixparadise.extensions.setImmersiveMode
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.gson.Gson
import kotlinx.android.synthetic.main.view_ingredients_dialog.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream

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

    fun setIngredientSelectedListener(ingredientSelectedListener: IngredientSelectedListener) {
        this.ingredientSelectedListener = ingredientSelectedListener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) = super.onCreateDialog(savedInstanceState).also {
        it.window?.setImmersiveMode()
    }

    override fun onStart() {
        super.onStart()

        val call = Api.drinkService.getIngredients()

        call.enqueue(object : Callback<com.csgames.mixparadise.api.Model.Result> {
            override fun onFailure(call: Call<com.csgames.mixparadise.api.Model.Result>?, t: Throwable?) {
                t?.printStackTrace()
            }

            override fun onResponse(call: Call<com.csgames.mixparadise.api.Model.Result>?, response: Response<com.csgames.mixparadise.api.Model.Result>?) {
                val viewManager = LinearLayoutManager(activity)
//                val viewAdapter = LiquidIngredientAdapter(response?.body()?.juices)

                var recyclerView = view?.findViewById<RecyclerView>(R.id.juices)
                recyclerView?.layoutManager = viewManager
//                recyclerView?.adapter = viewAdapter
            }
        })

        dialog?.findViewById<View>(R.id.design_bottom_sheet)?.let { bottomSheet ->
            BottomSheetBehavior.from(bottomSheet).isHideable = false
            bottomSheet.layoutParams?.height = ViewGroup.LayoutParams.MATCH_PARENT
        }
    }
}