package com.example.masjid_annur.api


import retrofit2.Call
import retrofit2.http.GET

interface KegiatanApi {
    @GET ("api/kegiatan")
    fun getKegiatanApi() : Call <List<ResponeKegiatan>>
}

data class ResponeKegiatan (
    val id : Int,
    val title : String,
    val description: String,
    val date : String
)