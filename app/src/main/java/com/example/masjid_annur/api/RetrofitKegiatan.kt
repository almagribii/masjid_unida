package com.example.authentication

import com.example.masjid_annur.api.KegiatanApi // Jika masih digunakan di tempat lain
import com.example.masjid_annur.api.MasjidBalanceApiService // <<-- IMPORT INI

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitKegiatan {
    private const val BASE_URL = "http://10.10.17.101:8081/"

    val mLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // BODY untuk detail request/response di Logcat
    }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(mLoggingInterceptor)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .connectTimeout(15, TimeUnit.SECONDS)
        .callTimeout(15, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    val instance: KegiatanApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(KegiatanApi::class.java)
    }

    val masjidBalanceApiServiceInstance: MasjidBalanceApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) // Penting untuk Call<T>
            .client(okHttpClient)
            .build()
            .create(MasjidBalanceApiService::class.java)
    }
}