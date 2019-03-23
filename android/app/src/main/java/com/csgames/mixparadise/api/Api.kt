package com.csgames.mixparadise.api

import android.content.Context
import okhttp3.Cache
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZoneOffset.*

object Api {
    private lateinit var retrofit: Retrofit

    fun init(context: Context) {
        val okHttpClient = OkHttpClient.Builder()
            .cache(Cache(File(context.cacheDir, "http-cache"), 10 * 1024 * 1024))
            .addInterceptor { chain ->
                val original = chain.request()

                var key = "csgames19-"
                val time = LocalDateTime.now().toEpochSecond(UTC) / 60
                key += time.toString()
                key += '-'
                key += "hello_there"
                // Request customization: add request headers
                val requestBuilder = original.newBuilder()
                    .header("Authorization", key) // <-- this is the important line

                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://mirego-csgames19.herokuapp.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val drinkService: DrinkService by lazy { retrofit.create(DrinkService::class.java) }
}