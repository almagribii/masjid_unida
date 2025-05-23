package com.example.masjid_annur.api

import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor (private val token: String) : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestWithToken = originalRequest.newBuilder()
            .header("x-acces-token", token)
            .build()
        return chain.proceed(requestWithToken)
    }
}