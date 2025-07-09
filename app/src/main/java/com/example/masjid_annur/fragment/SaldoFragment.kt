package com.example.masjid_annur.ui.balance // Sesuaikan package Anda

// Import yang relevan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.authentication.RetrofitKegiatan
import com.example.masjid_annur.R
import com.example.masjid_annur.api.MasjidBalance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SaldoFragment : Fragment() {

    // Deklarasi View sebagai properti kelas
    private lateinit var tvCurrentCashBalance: TextView
    private lateinit var tvMonthlyIncome: TextView
    private lateinit var tvMonthlyExpenditure: TextView
    private lateinit var tvEndOfMonthBaance: TextView
    private lateinit var tvMonthlyIncome2: TextView
    private lateinit var tvMonthlyExpenditure2: TextView
    private lateinit var error : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout untuk fragment ini
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi Views menggunakan findViewById() dari 'view' yang di-inflate
        tvCurrentCashBalance = view.findViewById(R.id.tv_saldo_kas)
        tvMonthlyIncome = view.findViewById(R.id.tv_pemasukan_bulan_ini)
        tvMonthlyIncome2 = view.findViewById(R.id.tv_total_pemasukan)
        tvMonthlyExpenditure = view.findViewById(R.id.tv_pengeluaran_bulan_ini)
        tvMonthlyExpenditure2 = view.findViewById(R.id.tv_total_pengeluaran)
        tvEndOfMonthBaance = view.findViewById(R.id.tv_saldo_akhir_bulan)
        error = view.findViewById(R.id.error)
    }

    fun fetchSaldo() {
        RetrofitKegiatan.masjidBalanceApiServiceInstance.getAllMasjidBalances()
            .enqueue(object : Callback<MasjidBalance> {
                override fun onResponse(p0: Call<MasjidBalance>, p1: Response<MasjidBalance>) {
                    if (p1.isSuccessful){
                        val saldo = p1.body()
                        saldo?.let {
                            error.visibility = View.GONE
                            tvCurrentCashBalance.text = saldo.currentCashBalance.toString()
                            tvMonthlyIncome.text = saldo.monthlyIncome.toString()
                            tvMonthlyIncome2.text = saldo.monthlyIncome.toString()
                            tvMonthlyExpenditure.text = saldo.monthlyExpenditure.toString()
                            tvMonthlyExpenditure2.text = saldo.monthlyExpenditure.toString()
                            tvEndOfMonthBaance.text = saldo.endOfMonthBalance.toString()
                        }
                    }else {
                        // Handle unsuccessful responses even if the server is up but returns an error (e.g., 404, 500)
                        error.visibility = View.VISIBLE
                        // You could also log this for debugging: Log.e("API_CALL", "Unsuccessful response: ${p1.code()} - ${p1.message()}")
                    }
                }

                override fun onFailure(p0: Call<MasjidBalance>, p1: Throwable) {

                }

            })
    }

}