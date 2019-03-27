package com.csgames.mixparadise.api

import com.csgames.mixparadise.helper.DigestHelper
import com.csgames.mixparadise.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ApiHelper {
    private var hasCacheInternal = false

    val hasCache: Boolean
        get() = hasCacheInternal

    fun getJuice(id: String, callback: (juice: Juice?) -> Unit) {
        getJuices { juices ->
            callback(juices?.first { juice ->
                juice.id == id
            })
        }
    }

    fun getDrink(id: String, callback: (drink: Drink?) -> Unit) {
        getDrinks { drinks ->
            callback(drinks?.first { drink ->
                drink.id == id
            })
        }
    }

    fun getIngredient(id: String, callback: (ingredient: Ingredient?) -> Unit) {
        getIngredients { ingredients ->
            callback(ingredients?.first { ingredient ->
                ingredient.id == id
            })
        }
    }

    fun getAlcohol(id: String, callback: (alcohol: Alcohol?) -> Unit) {
        getAlcohols { alcohols ->
            callback(alcohols?.first { alcohol ->
                alcohol.id == id
            })
        }
    }

    private fun getJuices(callback: (juices: List<Juice>?) -> Unit) {
        getIngredientResponse {
            callback(it?.juices)
        }
    }

    private fun getDrinks(callback: (drinks: List<Drink>?) -> Unit) {
        getIngredientResponse {
            callback(it?.drinks)
        }
    }

    private fun getIngredients(callback: (ingredients: List<Ingredient>?) -> Unit) {
        getIngredientResponse {
            callback(it?.ingredients)
        }
    }

    private fun getAlcohols(callback: (alcohols: List<Alcohol>?) -> Unit) {
        getIngredientResponse {
            callback(it?.alcohols)
        }
    }

    fun getIngredientResponse(callback: (ingredientResponse: IngredientResponse?) -> Unit) {
        val key = "mirego"
        val authorization = buildAuthorization(key)

        Api.drinkService.listIngredients(key, authorization).enqueue(object : Callback<IngredientResponse> {
            override fun onFailure(call: Call<IngredientResponse>, t: Throwable) {
                callback(null)
            }

            override fun onResponse(call: Call<IngredientResponse>, response: Response<IngredientResponse>) {
                if (response.isSuccessful) {
                    hasCacheInternal = true
                    callback(response.body())
                } else {
                    callback(null)
                }
            }
        })
    }

    fun getServeResponse(ingredients: List<IngredientRequest>, callback: (serveResponse: ServeResponse?) -> Unit) {
        Api.drinkService.serve(ingredients).enqueue(object : Callback<ServeResponse> {
            override fun onFailure(call: Call<ServeResponse>, t: Throwable) {
                callback(null)
            }

            override fun onResponse(call: Call<ServeResponse>, response: Response<ServeResponse>) {
                if (response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }
        })
    }

    private fun buildAuthorization(key: String) =
        DigestHelper.sha1("csgames19-${System.currentTimeMillis() / 1000 / 60}-$key")
}