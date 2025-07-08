package com.example.masjid_annur.api


import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import java.math.BigDecimal

interface KegiatanApi {
    @GET ("api/activities")
    fun getKegiatanApi() : Call <List<ResponeKegiatan>>
}


data class ResponeKegiatan (
    val id : Int,
    val title : String,
    val description: String,
    val activityDateTime : String,
    val location : String,
    val speaker : String,
    val imageUrl: String
)
