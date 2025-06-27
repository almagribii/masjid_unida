package com.example.masjid_annur.activity

import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.authentication.RetrofitClient
import com.example.masjid_annur.R
import com.example.masjid_annur.adapters.NoAyatAdapter
import com.example.masjid_annur.api.Ayat
import com.example.masjid_annur.api.SurahRespone
import retrofit2.Callback
import retrofit2.Response

class AyatActivity : AppCompatActivity () {

    private lateinit var recyclerView: RecyclerView
    private lateinit var ayatAdapter: NoAyatAdapter
    private lateinit var btnBack : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ayat)

        btnBack = findViewById(R.id.btnBback)
        recyclerView = findViewById(R.id.recyclerViewAyat)
        ayatAdapter = NoAyatAdapter(mutableListOf())
        recyclerView.adapter = ayatAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val surahNumber = intent.getIntExtra("surah_number", 1)
        val surahName = intent.getStringExtra("surah_name")
        ngambilData(surahNumber)

        btnBack.setOnClickListener {
            finish()
        }

    }
    private fun ngambilData(surahNumber: Int) {
        RetrofitClient.instance.getDetailSurah(surahNumber).enqueue(object :Callback<SurahRespone>{
            override fun onResponse(p0: retrofit2.Call<SurahRespone>, p1: Response<SurahRespone>) {
                if(p1.isSuccessful){
                    val listAyat = p1.body()?.data?.ayat
                    listAyat?.let {
                        ayatAdapter.updateData(it) // it adalah List<Ayat>
                    }

                }
            }

            override fun onFailure(p0: retrofit2.Call<SurahRespone>, p1: Throwable) {
                Log.e("API_ERROR", p1.message ?: "Unknown error")
            }
        })
    }

}