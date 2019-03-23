package com.csgames.mixparadise.api

import android.content.Context
import android.util.Log.d
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.HTTP
import java.io.File
import java.io.IOException
import java.security.MessageDigest
import java.security.spec.MGF1ParameterSpec.SHA1
import java.util.*

object Api {
    private lateinit var retrofit: Retrofit
    val KEY = "better_authenticate_yourself_for_those_sweet_drinks_hihi"

    fun sha1(input: String) = hashString("SHA-1", input)
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
            .addInterceptor { chain ->
                val original = chain.request()

                //Add an HTTP Header named Authorization containing the SHA1 checksum of a concatenation of the following strings, joined by a - dash:
                //The static csgames19 strings
                //        The current epoch time in minutes (epochSeconds / 60)
                //The same string as the key parameter
                val time = (System.currentTimeMillis() / 1000) / 60
                val auth_key = "csgames19-${time}-${Api.KEY}"

                d("Generating auth header:", auth_key)
                d("Generating sha1 header:", sha1(auth_key).toString().toLowerCase())

                // Request customization: add request headers
                val requestBuilder = original.newBuilder()
                    .header("Team", "ETS1")
                    .header("Authorization", sha1(auth_key).toString().toLowerCase())

                val request = requestBuilder.build()
                chain.proceed(request)
            }
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