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
    private lateinit var tvErrorMessage: TextView
    private lateinit var tvMonthlyIncome2: TextView
    private lateinit var tvMonthlyExpenditure2: TextView

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
        tvErrorMessage = view.findViewById(R.id.tv_saldo_akhir_bulan)
        fetchSaldo()
    }

    fun fetchSaldo() {
        RetrofitKegiatan.masjidBalanceApiServiceInstance.getAllMasjidBalances()
            .enqueue(object : Callback<MasjidBalance> {
                override fun onResponse(p0: Call<MasjidBalance>, p1: Response<MasjidBalance>) {
                    if (p1.isSuccessful){
                        val saldo = p1.body()
                        saldo?.let {
                            tvCurrentCashBalance.text = saldo.currentCashBalance.toString()
                            tvMonthlyIncome.text = saldo.monthlyIncome.toString()
                            tvMonthlyIncome2.text = saldo.monthlyIncome.toString()
                            tvMonthlyExpenditure.text = saldo.monthlyExpenditure.toString()
                            tvMonthlyExpenditure2.text = saldo.monthlyExpenditure.toString()
                            tvErrorMessage.text = saldo.endOfMonthBalance.toString()
                        }
                    }
                }

                override fun onFailure(p0: Call<MasjidBalance>, p1: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

}