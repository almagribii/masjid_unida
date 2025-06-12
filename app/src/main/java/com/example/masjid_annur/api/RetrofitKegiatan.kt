package com.example.authentication

import com.example.masjid_annur.api.AuthInterceptor
import com.example.masjid_annur.api.KegiatanApi
import com.example.masjid_annur.api.QuranApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitKegiatan {
    private const val BASE_URL = "localhost:8081/api/kegiatan" // Ganti dengan URL API Anda
    val mLoggingInterceptor = HttpLoggingInterceptor().apply {
        level=HttpLoggingInterceptor.Level.BASIC
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


}
