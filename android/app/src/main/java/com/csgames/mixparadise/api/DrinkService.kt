package com.csgames.mixparadise.api

import android.database.Observable
import android.util.Log
import com.csgames.mixparadise.model.StackedIngredient
import retrofit2.http.GET
//import jdk.nashorn.internal.runtime.ScriptingFunctions.readLine
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.http.Headers


interface DrinkService {

    @Headers("Team:UQO")
    @GET("Ingredients")
    fun getIngredient(): Call<ArrayList<StackedIngredient>>


    // TODO: serve call
}