package com.example.masjid_annur.api

import com.google.android.gms.common.api.Response
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

    @GET("quran/juz/semua")
    fun getAllJuz(): Call<QuranJuzResponse>

    @GET("hadits/arbain/semua")
    fun getHaditsArbain(): Call<HaditsResponse>

    @GET("hadits/arbain/{number}")
    fun getDetailHaditsArbain(
        @Path("number") number: String
    ): Call<HaditsResponse2>
}


//Surah dan daftarnya
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


//JUZ DAN DAFTARNYA
data class QuranJuzResponse(
    val status: Boolean,
    val request: Request,
    val data: List<Juz>
)

data class Request(
    val path: String
)

data class Juz(
    val ayat_arab: String,
    val ayat_indo: String,
    val ayat_latin: String,
    val name: String,
    val name_end_arab: String,
    val name_end_id: String,
    val name_start_arab: String,
    val name_start_id: String,
    val number: String,
    val surah_id_end: String,
    val surah_id_start: String,
    val verse_end: String,
    val verse_start: String
)

//HADITS ARBAIN
data class HaditsResponse(
    val status: Boolean,
    val request: Requestt,
    val info: Info,
    val data: List<Hadith>
)

data class HaditsResponse2(
    val status: Boolean,
    val request: Requestt,
    val info: Info,
    val data: Hadith
)

data class Requestt(
    val path: String
)

data class Info(
    val min: Int,
    val max: Int
)

data class Hadith(
    val arab: String,
    val indo: String,
    val judul: String,
    val no: String
)
