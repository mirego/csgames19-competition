package com.csgames.mixparadise.api

import com.csgames.mixparadise.HashUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.sql.Time

class NoAuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        chain?.let {
            val requestBuilder = it.request().newBuilder()
            val request = requestBuilder
                .addHeader("Team", "BatailleLaval")
                .build()
            return chain.proceed(request)
        }


        throw IOException("Can'intercept the request")
    }
}
