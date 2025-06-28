//package com.example.masjid_annur.api
//
//
//import retrofit2.Call
//import retrofit2.http.GET
//import retrofit2.http.Path
//
//interface QuranComApi {
//    @GET("/v1/juz/{juz_number}/quran-uthmani")
//    fun getJuzByNumber(
//        @retrofit2.http.Path("juz_number") juzNumber: Int
//    ): Call<JuzDetailResponse>
//}
//
//data class JuzDetailResponse(
//    val code: Int,
//    val status: String,
//    val data: List<Juz>
//)
//
//data class Juz(
//    val number: Int,
//    val name: String,
//    val surahs: Map<String, Surah>
//)
//
//data class Surah(
//    val number: Int,
//    val name: String,
//    val englishName: String,
//    val englishNameTranslation: String,
//    val revelationType: String,
//    val numberOfAyahs: Int
//)

