package com.csgames.mixparadise.api

import android.annotation.TargetApi
import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.security.MessageDigest
import java.time.LocalDateTime
import java.time.ZoneOffset

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

    @TargetApi(26)
    fun createHeader(key: String = "tv8PDnId7ylIwGEQ5naooq3wnL205RNR"): String {

        val now = LocalDateTime.now(ZoneOffset.UTC)
        // LocalDateTime to epoch seconds
        val minutes = now.atZone(ZoneOffset.UTC).toEpochSecond()/60

        val text  = "csgames19-" + minutes + "-" + key
        print(text)
        println()

        val bytes = text.toByteArray()
        val md = MessageDigest.getInstance("SHA-1")
        val digest = md.digest(bytes)
        var stringKey = ""
        for (byte in digest) stringKey+= "%02x".format(byte)
        println()
        return stringKey
    }

    val drinkService: DrinkService by lazy { retrofit.create(DrinkService::class.java) }
}