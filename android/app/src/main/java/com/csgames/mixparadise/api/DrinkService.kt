package com.csgames.mixparadise.api

import com.csgames.mixparadise.model.IngredientList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface DrinkService {
    // TODO: get ingredients
    @Headers("Team: xargonauts")
    @GET("ingredients")
    fun getIngredients(): Call<IngredientList>

    // TODO: serve call
    @Headers("Team: xargonauts")
    @POST("serve")
    fun submitRecipe(): Call<Recipe>
 /*
 “I see Jesus in every human being.
 I say to myself, this is hungry Jesus, I must feed him.
 This is sick Jesus. This one has leprosy or gangrene;
 I must wash him and tend to him.

 I serve because I love Jesus.”
― Mother Teresa

"I serve because I love CSGAMES!
― Xargonauts
  */
}