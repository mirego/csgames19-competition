package com.csgames.mixparadise.api

import com.csgames.mixparadise.model.api.IngredientsResponse
import com.csgames.mixparadise.model.api.ServeResponse
import com.csgames.mixparadise.model.api.ServedIngredient
import io.reactivex.Observable
import retrofit2.http.*

interface DrinkService {

    @GET("ingredients")
    fun ingredients(): Observable<IngredientsResponse>

    @GET("ingredients")
    fun ingredients(@Header("Authorization") authorizationHeader: String, @Query("key") key: String): Observable<IngredientsResponse>

    @POST("/serve")
    fun serve(@Body serveRequest: List<ServedIngredient>): Observable<ServeResponse>
}