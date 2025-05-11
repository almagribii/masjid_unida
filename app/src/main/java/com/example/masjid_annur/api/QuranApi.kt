package com.example.masjid_annur.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface QuranApi {
    @GET("/api/v2/surat")
    fun getJuzapi(): Call<JuzRespone>

    @GET("api/v2/surat/{nomor}")
    fun getDetailSurah(
        @Path("nomor") nomor: Int
    ): Call<SurahRespone>
}

data class JuzRespone(
    val code: Int,
    val message: String,
    val data: List<JuzApi>
)

data class JuzApi(
    val nomor: Int,
    val nama: String,
    val namaLatin: String,
    val jumlahAyat: Int,
    val tempatTurun: String,
    val arti: String,
    val deskripsi: String
)

data class SurahRespone(
    val code: Int,
    val message: String,
    val data: SurahDetail
)

data class SurahDetail(
    val nomor: Int,
    val nama: String,
    val namaLatin: String,
    val jumlahAyat: Int,
    val tempatTurun: String,
    val arti: String,
    val deskripsi: String,
    val audioFull: Map<String, String>,
    val ayat: List<Ayat>,
    val suratSelanjutnya: SuratSelanjutnya?,
    val suratSebelumnya: Any? // Bisa false atau object, jadi pakai Any?
)

data class Ayat(
    val nomorAyat: Int,
    val teksArab: String,
    val teksLatin: String,
    val teksIndonesia: String,
    val audio: Map<String, String>
)

data class SuratSelanjutnya(
    val nomor: Int,
    val nama: String,
    val namaLatin: String,
    val jumlahAyat: Int
)
