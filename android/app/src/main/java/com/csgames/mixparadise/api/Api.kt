package com.csgames.mixparadise.api

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import okhttp3.HttpUrl
import okhttp3.Interceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.time.LocalDateTime
import java.time.ZoneOffset
import android.provider.SyncStateContract.Helpers.update
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


object Api {
    private lateinit var retrofit: Retrofit
    private val key = "tv8PDnId7ylIwGEQ5naooq3wnL205RNR";

    fun init(context: Context) {
        val okHttpClient = OkHttpClient.Builder()
            .cache(Cache(File(context.cacheDir, "http-cache"), 10 * 1024 * 1024))
            .build()

        //added interceptor so that all query have a specific header
        val interceptor = Interceptor { chain ->

            // TODO :: do the proper sha1 encryption
            //val timestamp = LocalDateTime.now(ZoneOffset.UTC).toEpochSecond())
            val autorization : String = "csgames19-"

            val request = chain.request()
                .newBuilder()
                .addHeader("Team", "McBrooke")
                //.addHeader()
                .build()


            chain.proceed(request)
        }

        retrofit = Retrofit.Builder()
            .baseUrl("https://mirego-csgames19.herokuapp.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val drinkService: DrinkService by lazy { retrofit.create(DrinkService::class.java) }



    // do the ingredients api call asynchronously
    fun ingredients() {
        val call = Api.drinkService.ingredients()
        call.enqueue(object : Callback<IngredientResponse.Result> {

            override fun onFailure(call: Call<IngredientResponse.Result>?, t: Throwable?) {
                throw error("an Error Has Occured")
            }

            override fun onResponse(call: Call<IngredientResponse.Result>?,
                                    response: Response<IngredientResponse.Result>?) {
                return print(response!!.body()!!);
            }
        })
    }

    //TODO :: add an other async api call for server



}


