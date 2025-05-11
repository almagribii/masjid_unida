package com.example.masjid_annur.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.masjid_annur.R
import com.example.masjid_annur.activity.AyatActivity
import com.example.masjid_annur.adapters.NoJuzAdapter
import com.example.masjid_annur.adapters.NoSurahAdapter
import com.example.masjid_annur.api.Juz
import com.example.masjid_annur.api.JuzDetailResponse


import com.example.masjid_annur.api.RetrofitQuranCom
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JuzFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var juzAdapter : NoJuzAdapter
    private val juzList = mutableListOf<Juz>()  // Tipe data yang benar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_juz, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        juzAdapter = NoJuzAdapter(juzList) { selectedJuz ->
            val intent = Intent(requireContext(), AyatActivity::class.java)
            intent.putExtra("juz_number", selectedJuz.number)
            startActivity(intent)
        }
        recyclerView.adapter = juzAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        ambilData()
        return view
    }

    private fun ambilData() {
        for (i in 1..30) {
            RetrofitQuranCom.instance.getJuzByNumber(i)
                .enqueue(object : Callback<JuzDetailResponse> {
                    override fun onResponse(
                        call: Call<JuzDetailResponse>,
                        response: Response<JuzDetailResponse>
                    ) {
                        if (response.isSuccessful) {
                            val juzData = response.body()?.data
                            juzData?.let {
                                juzList.addAll(it) // Menambahkan data ke list
                                juzAdapter.notifyDataSetChanged() // Menyegarkan RecyclerView
                            }
                            Log.d("JuzLoop", "Juz $i berhasil dimuat")
                        } else {
                            Log.e("JuzLoop", "Gagal ambil Juz $i: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<JuzDetailResponse>, t: Throwable) {
                        Log.e("JuzLoop", "Error ambil Juz $i: ${t.message}")
                    }
                })
        }
    }
}
