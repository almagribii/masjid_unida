package com.example.masjid_annur.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.masjid_annur.R
import com.example.masjid_annur.activity.AyatActivity
import com.example.masjid_annur.adapters.NoJuzAdapter
import com.example.masjid_annur.adapters.NoSurahAdapter
import com.example.masjid_annur.api.Juz
import com.example.masjid_annur.api.QuranJuzResponse
import com.example.masjid_annur.api.RetrofitJuz


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
        RetrofitJuz.instance.getAllJuz().enqueue(object : Callback<QuranJuzResponse> {
            override fun onResponse(p0: Call<QuranJuzResponse>, p1: Response<QuranJuzResponse>) {
                if (p1.isSuccessful) {
                    val data = p1.body()?.data ?: emptyList()
                    juzAdapter.updateData(data)
                }
            }

            override fun onFailure(p0: Call<QuranJuzResponse>, p1: Throwable) {
                Toast.makeText(requireContext(), "Gagal ambil data: ${p1.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
