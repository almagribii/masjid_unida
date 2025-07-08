package com.example.masjid_annur.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.math.BigDecimal

interface MasjidBalanceApiService {
    @GET("api/masjid-balances/1") // Endpoint GET semua balances
    fun getAllMasjidBalances(): Call<MasjidBalance>

}

// Data class MasjidBalance Anda (tidak ada perubahan)
data class MasjidBalance(
    val id: Long,
    val date: String, // LocalDate dari Spring Boot akan diterima sebagai String "YYYY-MM-DD"
    @SerializedName("currentCashBalance")
    val currentCashBalance: BigDecimal?, // Nullable jika di Spring Boot bisa null
    @SerializedName("monthlyIncome")
    val monthlyIncome: BigDecimal?,
    @SerializedName("monthlyExpenditure")
    val monthlyExpenditure: BigDecimal?,
    @SerializedName("beginningOfMonthBalance")
    val beginningOfMonthBalance: BigDecimal?,
    @SerializedName("endOfMonthBalance")
    val endOfMonthBalance: BigDecimal?
)