package com.csgames.mixparadise.api

import android.content.Context
import android.preference.PreferenceManager
import com.csgames.mixparadise.HashUtils
import com.csgames.mixparadise.R
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.security.MessageDigest

class HeadersInterceptor () : Interceptor {
        override fun intercept(chain: Interceptor.Chain?): Response {
            chain?.let {
                val timeLong = System.currentTimeMillis() / 1000 / 60
                val time = timeLong.toString()
                val textToHash = "csgames19-$time"
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
