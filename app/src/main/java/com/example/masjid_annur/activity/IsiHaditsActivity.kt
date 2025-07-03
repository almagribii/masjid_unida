package com.example.masjid_annur.activity

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.masjid_annur.R
import com.example.masjid_annur.api.HaditsResponse
import com.example.masjid_annur.api.HaditsResponse2
import com.example.masjid_annur.api.RetrofitJuz
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IsiHaditsActivity : AppCompatActivity() {

    private lateinit var btnBack: LinearLayout
    private var hadithNumber: String? = null
    private lateinit var tvArab: TextView
    private lateinit var tvIndo: TextView
    private lateinit var tvNo: TextView
    private lateinit var tvJudul: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_isi_hadits)

        btnBack = findViewById(R.id.btnBack)
        tvArab = findViewById(R.id.tvHaditsArab)
        tvIndo = findViewById(R.id.artiHadits)
        tvNo = findViewById(R.id.tvNo)
        tvJudul = findViewById(R.id.judul)
        btnBack.setOnClickListener {
            finish()
        }

        hadithNumber = intent.getStringExtra("HADITH_NUMBER")
        if (hadithNumber != null) {
            ambilIsiHadits(hadithNumber!!) // Panggil fungsi dengan nomor Hadis
        } else {
            Toast.makeText(this, "Nomor Hadis tidak ditemukan.", Toast.LENGTH_SHORT).show()
            finish() // Tutup aktivitas jika nomor Hadis tidak ada
        }

    }

    fun ambilIsiHadits(number: String) {
        RetrofitJuz.instance.getDetailHaditsArbain(number)
            .enqueue(object : Callback<HaditsResponse2> {
                override fun onResponse(p0: Call<HaditsResponse2>, p1: Response<HaditsResponse2>) {
                    if (p1.isSuccessful) {
                        Log.d("Isi Hadits Activity", "Data Hadits No: $number berhasil diambil")
                        val hadits = p1.body()?.data
                        tvNo.text = hadits?.no
                        tvArab.text = hadits?.arab
                        tvIndo.text = hadits?.indo
                        tvJudul.text = hadits?.judul
                    }
                }

                override fun onFailure(p0: Call<HaditsResponse2>, p1: Throwable) {
                    Log.d("Isi Data Hadits Gagal Diambil", "${p1.message}")
                    Toast.makeText(
                        this@IsiHaditsActivity,
                        "Gagal mengambil data.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
    }
}