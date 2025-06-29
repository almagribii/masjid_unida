package com.example.masjid_annur.activity

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.masjid_annur.R
import com.example.masjid_annur.adapters.HaditsAdapter
import com.example.masjid_annur.api.Hadith
import com.example.masjid_annur.api.HaditsResponse
import com.example.masjid_annur.api.RetrofitJuz
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HaditsActivity : AppCompatActivity() {
    private lateinit var recyclerHadits: RecyclerView
    private lateinit var haditsAdapter: HaditsAdapter
    private val haditsList = mutableListOf<Hadith>()
    private lateinit var btnBack : LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hadits)

        btnBack = findViewById(R.id.btnBack)
        recyclerHadits = findViewById(R.id.recyclerHadits)
        haditsAdapter = HaditsAdapter(haditsList) { selectedHadits ->
        }
        btnBack.setOnClickListener {
            finish()
        }
        recyclerHadits.adapter = haditsAdapter
        recyclerHadits.layoutManager = LinearLayoutManager(this)
        ambilHadits()
    }

    fun ambilHadits() {
        RetrofitJuz.instance.getHaditsArbain().enqueue(object : Callback<HaditsResponse> {
            override fun onResponse(p0: Call<HaditsResponse>, p1: Response<HaditsResponse>) {
                if(p1.isSuccessful) {
                    val hadits = p1.body()?.data?: emptyList()
                    haditsAdapter.updateData(hadits)
                }
            }

            override fun onFailure(p0: Call<HaditsResponse>, p1: Throwable) {
                Toast.makeText(this@HaditsActivity, "Gagal mengambil data.", Toast.LENGTH_SHORT).show()            }

        })
    }
}