package com.csgames.mixparadise.api

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

object Api {
    private lateinit var retrofit: Retrofit

    fun init(context: Context) {
        val okHttpClient = OkHttpClient.Builder()
            .cache(Cache(File(context.cacheDir, "http-cache"), 10 * 1024 * 1024))
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://mirego-csgames19.herokuapp.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val drinkService: DrinkService by lazy { retrofit.create(DrinkService::class.java) }
}