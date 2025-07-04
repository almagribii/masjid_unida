package com.example.masjid_annur.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitNews {
    private const val BASE_URL = "https://artikel-islam.netlify.app/.netlify/"

    val mLoggingInterceptor = HttpLoggingInterceptor().apply {
        level= HttpLoggingInterceptor.Level.BASIC
    }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(mLoggingInterceptor)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .connectTimeout(15, TimeUnit.SECONDS)
        .callTimeout(15, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()


    val instance : NewsApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(NewsApi::class.java)
    }
}