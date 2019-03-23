package com.csgames.mixparadise.api

import android.telephony.CellSignalStrength
import retrofit2.Call;
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

/*
val ingred = Api.drinkService.ingredients()
        ingred.enqueue(object : Callback<IngredientResponse.Result> {
            override fun onFailure(call: Call<IngredientResponse.Result>?, t: Throwable?) {
                print("aaa")
            }

            override fun onResponse(call: Call<IngredientResponse.Result>?,
                                    response: Response<IngredientResponse.Result>?) {
                print(response!!.body()!!.drinks.get(0).color)
            }
        })
 */

interface DrinkService {
    @Headers(
        "Team: McBrooke"
    )
    @GET("/ingredient")
    fun ingredients(): Call<IngredientResponse.Result>

    @GET("/serve")
    fun serve(id: String, qty: Int): Call<ServeResponse.Result>
}

// TODO : move to an other file
object IngredientResponse {
    data class Result(
        val juices: List<Liquid>,
        val drinks: List<Liquid>,
        val ingredients: List<Solid>
    )

    data class Solid(
        val id: String,
        val label: String,
        val type: String = "solid",
        val weight: Double,
        val imageUrl: String,
        val sprites: List<String>
    );

    data class Liquid(
        val id: String,
        val label: String,
        val type: String = "liquid",
        val color: String,
        val opacity: Double,
        val imageUrl: String
    );
}

object ServeResponse {

    data class Result(
        val rating : Rating,
        val review: Review
    )

    data class Rating(
        val note: Int,
        val comment: String
    )

    data class Review(
        val taste: Int,
        val volume: Int,
        val strength: Int
    )


}




// NOTE :: will be used if we can reach this point
fun<T> Call<T>.enqueue(callback: CallBackKt<T>.() -> Unit) {
    val callBackKt = CallBackKt<T>()
    callback.invoke(callBackKt)
    this.enqueue(callBackKt)
}

class CallBackKt<T>: Callback<T> {

    var onResponse: ((Response<T>) -> Unit)? = null
    var onFailure: ((t: Throwable?) -> Unit)? = null

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure?.invoke(t)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onResponse?.invoke(response)
    }

}