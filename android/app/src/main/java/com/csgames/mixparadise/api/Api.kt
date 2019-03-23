package com.csgames.mixparadise.api

import android.content.Context
import okhttp3.Cache
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.security.MessageDigest
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZoneOffset.*

object Api {
    private lateinit var retrofit: Retrofit

    private fun hashString(type: String, input: String): String {
        val HEX_CHARS = "0123456789ABCDEF"
        val bytes = MessageDigest
            .getInstance(type)
            .digest(input.toByteArray())
        val result = StringBuilder(bytes.size * 2)

        bytes.forEach {
            val i = it.toInt()
            result.append(HEX_CHARS[i shr 4 and 0x0f])
            result.append(HEX_CHARS[i and 0x0f])
        }

        return result.toString()
    }

    fun init(context: Context) {
        val okHttpClient = OkHttpClient.Builder()
            .cache(Cache(File(context.cacheDir, "http-cache"), 10 * 1024 * 1024))
            .addInterceptor { chain ->
                val original = chain.request()

                var key = "csgames19-"
                val time = LocalDateTime.now(ZoneOffset.UTC).atZone(ZoneOffset.UTC).toEpochSecond() / 60
                key += time.toString()
                key += '-'
                key += "tv8PDnId7ylIwGEQ5naooq3wnL205RNR"

                println(key)
                println(hashString("SHA-1",key).toLowerCase())

                // Request customization: add request headers
                val requestBuilder = original.newBuilder()
                    .header("Authorization", hashString("SHA-1",key).toLowerCase()) // <-- this is the important line
                    .header("Team", "SherGill")

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