package com.example.masjid_annur.activity

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.masjid_annur.R
import com.example.masjid_annur.adapters.DoaAdapter
import com.example.masjid_annur.api.DoaSumberRespone
import com.example.masjid_annur.api.RetrofitJuz
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoaActivity : AppCompatActivity () {
    private lateinit var doaAdapter : DoaAdapter
    private lateinit var recyclerDoa : RecyclerView
    private val doaList = mutableListOf<String>()
    private lateinit var btnBack : LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_doa)

        btnBack = findViewById(R.id.btnBack)
        recyclerDoa = findViewById(R.id.recyclerDoa)
        doaAdapter = DoaAdapter(doaList)

        btnBack.setOnClickListener {
            finish()
        }

        recyclerDoa.adapter = doaAdapter
        recyclerDoa.layoutManager = LinearLayoutManager(this)
        ambilDoaSumber()
    }

    fun ambilDoaSumber() {
        RetrofitJuz.instance.getDoaSumber().enqueue(object : Callback<DoaSumberRespone> {
            override fun onResponse(call: Call<DoaSumberRespone>, response: Response<DoaSumberRespone>) {
                if (response.isSuccessful) {
                    val doa  = response.body()?.data ?: emptyList()
                    doaAdapter.updateData(doa)
                }
            }

            override fun onFailure(call: Call<DoaSumberRespone>, t: Throwable) {
                Toast.makeText(this@DoaActivity, "Kesalahan jaringan: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}