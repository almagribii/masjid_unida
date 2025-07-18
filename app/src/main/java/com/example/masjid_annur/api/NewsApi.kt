package com.example.masjid_annur.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsApi {
    @GET("functions/api/msh")
    fun getNewNews(): Call<ArticleApiResponse>
}

data class ArticleApiResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: ArticleDataWrapper // Ini akan memetakan ke objek 'data' utama
)

// 2. Model untuk objek 'data' yang bersarang setelah root, yang berisi pagination dan list artikel
data class ArticleDataWrapper(
    @SerializedName("pagination") val pagination: Pagination,
    @SerializedName("data") val articles: List<ArticleItem> // Ini akan memetakan ke array 'data'
)

// 3. Model untuk informasi paginasi
data class Pagination(
    @SerializedName("total_page") val totalPage: String
)

// 4. Model untuk setiap item artikel individu (ini adalah ArticleData Anda sebelumnya)
data class ArticleItem(
    @SerializedName("id") val id: String,
    @SerializedName("date") val date: String,
    @SerializedName("date_time") val dateTime: String,
    @SerializedName("author") val author: String?, // Dibuat nullable karena bisa kosong
    @SerializedName("author_link") val authorLink: String?, // Dibuat nullable
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String,
    @SerializedName("type") val type: String,
    @SerializedName("categories") val categories: List<String>? // Dibuat nullable
)