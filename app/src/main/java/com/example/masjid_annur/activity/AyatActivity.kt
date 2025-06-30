package com.example.masjid_annur.activity

import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.authentication.RetrofitClient
import com.example.masjid_annur.R
import com.example.masjid_annur.adapters.NoAyatAdapter
import com.example.masjid_annur.api.Ayat
import com.example.masjid_annur.api.DoaAcakRespone
import com.example.masjid_annur.api.Juz
import com.example.masjid_annur.api.JuzRespone
import com.example.masjid_annur.api.RetrofitJuz
import com.example.masjid_annur.api.SurahRespone
import retrofit2.Callback
import retrofit2.Response

class AyatActivity : AppCompatActivity () {

    private lateinit var recyclerView: RecyclerView
    private lateinit var ayatAdapter: NoAyatAdapter
    private lateinit var btnBack : LinearLayout
    private lateinit var tvSurat: TextView
    private lateinit var tvArtSurat: TextView
    private lateinit var tvTempat: TextView
    private lateinit var tvJumlahAyat: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ayat)

        btnBack = findViewById(R.id.btnBack)
        tvSurat = findViewById(R.id.namaSurat)
        tvArtSurat = findViewById(R.id.text_arti_surat)
        tvJumlahAyat = findViewById(R.id.text_jumlah_ayat)
        tvTempat = findViewById(R.id.text_tempat_turun)
        recyclerView = findViewById(R.id.recyclerViewAyat)
        ayatAdapter = NoAyatAdapter(mutableListOf())
        recyclerView.adapter = ayatAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val surahNumber = intent.getIntExtra("surah_number", 1)
        val surahName = intent.getStringExtra("surah_name")
        ngambilData(surahNumber)
        ambilLatinSurat(surahNumber)

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

    fun ambilLatinSurat(surahNumber: Int) {
        RetrofitClient.instance.getDetailSurah(surahNumber).enqueue(object : Callback<SurahRespone> {
            override fun onResponse(p0: retrofit2.Call<SurahRespone>, p1: Response<SurahRespone>) {
                if (p1.isSuccessful){
                    Log.d("Cek", "Keambil Datanya")
                    val suratLatin = p1.body()?.data
                    tvSurat.text = suratLatin?.namaLatin
                    tvTempat.text = suratLatin?.tempatTurun
                    tvJumlahAyat.text = suratLatin?.jumlahAyat.toString()
                    tvArtSurat.text = suratLatin?.arti
                }

            }

            override fun onFailure(p0: retrofit2.Call<SurahRespone>, p1: Throwable) {
                Toast.makeText(this@AyatActivity, "Gagal mengambil data.", Toast.LENGTH_SHORT).show()
            }

        })
    }

}