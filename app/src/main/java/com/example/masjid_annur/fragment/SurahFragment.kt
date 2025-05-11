package com.example.masjid_annur.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.authentication.RetrofitClient
import com.example.masjid_annur.R
import com.example.masjid_annur.activity.AyatActivity
import com.example.masjid_annur.adapters.NoSurahAdapter
import com.example.masjid_annur.api.JuzRespone
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SurahFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var surahAdapter: NoSurahAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_surah, container, false)
        recyclerView = view.findViewById(R.id.recyclerview)
        surahAdapter = NoSurahAdapter(mutableListOf()) { selectedSurah ->
            val intent = Intent(requireContext(), AyatActivity::class.java)
            intent.putExtra("surah_number", selectedSurah.nomor)
            intent.putExtra("surah_name", selectedSurah.namaLatin)
            startActivity(intent)
        }
        recyclerView.adapter = surahAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        ambilData()
        return view
    }

    private fun ambilData() {
        RetrofitClient.instance.getJuzapi().enqueue(object : Callback<JuzRespone> {
            override fun onResponse(call: Call<JuzRespone>, response: Response<JuzRespone>) {
                if (response.isSuccessful) {
                    val data = response.body()?.data ?: emptyList()
                    surahAdapter.updateData(data)
                }
            }

            override fun onFailure(call: Call<JuzRespone>, t: Throwable) {
                Toast.makeText(requireContext(), "Gagal ambil data: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
