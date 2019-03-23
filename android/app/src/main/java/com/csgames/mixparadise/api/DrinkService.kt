package com.csgames.mixparadise.api

import com.csgames.mixparadise.model.IngredientList
import com.csgames.mixparadise.model.RecipeIngredient
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface DrinkService {

    @Headers("Team: xargonauts")
    @GET("ingredients")
    fun getIngredients(): Call<IngredientList>


    @Headers("Team: xargonauts")
    @POST("serve")
    fun submitRecipe(): Call<RecipeIngredient>
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