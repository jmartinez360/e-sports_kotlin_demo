package com.example.lolresults.utils

import android.content.Context
import com.example.lolresults.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeadersInterceptor constructor(private val context: Context): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val request: Request = original.newBuilder()
            .header("Authorization", "Bearer ${BuildConfig.API_TOKEN}")
            .method(original.method, original.body)
            .build()

        return chain.proceed(request)
    }
}