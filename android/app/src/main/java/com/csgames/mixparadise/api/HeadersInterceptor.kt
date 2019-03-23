package com.csgames.mixparadise.api

import android.text.format.Time
import com.csgames.mixparadise.HashUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HeadersInterceptor(val key: String) : Interceptor {
        override fun intercept(chain: Interceptor.Chain?): Response {
            chain?.let {
                val time = System.currentTimeMillis()/1000/60
                val textToHash = "csgames19-$time-$key"
                val token  =  HashUtils().sha1( textToHash)

                val requestBuilder = it.request().newBuilder()
                val request = requestBuilder
                    .addHeader("Authorization", token)
                    .build()
                return chain.proceed(request)
            }


            throw IOException("Can'intercept the request")
        }
    }
