package com.csgames.mixparadise.api

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

object Api {
    private lateinit var retrofit: Retrofit

    fun init(context: Context) {
        val okHttpClient = OkHttpClient.Builder()
            .cache(Cache(File(context.cacheDir, "http-cache"), 10 * 1024 * 1024))
            .addInterceptor {
                val original = it.request()

                val request = original.newBuilder()
                    .addHeader("team", "polylinux1")
                    .method(original.method(), original.body())
                    .build()

                it.proceed(request)
            }
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://mirego-csgames19.herokuapp.com")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val drinkService: DrinkService by lazy { retrofit.create(DrinkService::class.java) }
}