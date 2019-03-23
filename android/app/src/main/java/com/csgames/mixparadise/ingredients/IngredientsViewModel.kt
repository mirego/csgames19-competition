package com.csgames.mixparadise.ingredients

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.csgames.mixparadise.BaseViewModel
import com.csgames.mixparadise.api.Api
import com.csgames.mixparadise.model.Ingredient
import com.csgames.mixparadise.model.IngredientsResponse
import io.reactivex.schedulers.Schedulers

class IngredientsViewModel : BaseViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading
    private val _hasError = MutableLiveData<Boolean>()
    val hasError: LiveData<Boolean>
        get() = _hasError
    private val _ingredients = MutableLiveData<IngredientsResponse>()
    val ingredients: LiveData<IngredientsResponse>
        get() = _ingredients

    private val blenderIngredients = MutableLiveData<List<Ingredient>>()

    init {
        _isLoading.value = false
        _hasError.value = false
        blenderIngredients.value = listOf()
    }

    fun fetchIngredients() {
        subscriptions.add(
            Api.drinkService.getIngredients()
                .subscribeOn(Schedulers.io())
                .subscribe { ingredients, err ->
                    _isLoading.postValue(false)
                    if (err != null) {
                        Log.e(this::class.java.simpleName, "Error", err)
                        _hasError.postValue(true)
                    } else {
                        _hasError.postValue(false)
                        _ingredients.postValue(ingredients)
                    }
                }
        )
    }

    fun addIngredient(ingredient: Ingredient) {
        blenderIngredients.value = blenderIngredients.value!! + ingredient
    }

    fun serveBeverage() {
        subscriptions.add(Api.drinkService.serveBeverage()
            .subscribeOn(Schedulers.io())
            .subscribe {

            })
    }
}