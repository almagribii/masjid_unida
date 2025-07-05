package com.example.masjid_annur.activity

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.masjid_annur.R

class SyaratDanKetentuanActivity : AppCompatActivity(){
    private lateinit var btnBack : LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_syarat_ketentuan)

        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }
        val tvContent: TextView = findViewById(R.id.tv_syarat_ketentuan_content)

        // Bagian ini adalah kuncinya:
        // Menggunakan Html.fromHtml() untuk merender string yang mengandung tag HTML
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // Untuk Android N (API 24) ke atas
            tvContent.text = Html.fromHtml(getString(R.string.konten_syarat_dan_ketentuan), Html.FROM_HTML_MODE_LEGACY)
        } else {
            // Untuk versi Android di bawah N
            @Suppress("DEPRECATION") // Menekan peringatan deprecation untuk versi lama
            tvContent.text = Html.fromHtml(getString(R.string.konten_syarat_dan_ketentuan))
        }

        // Jangan lupa juga untuk menambahkan fungsionalitas untuk btnBack jika belum ada
        findViewById<LinearLayout>(R.id.btnBack).setOnClickListener {
            onBackPressed()
        }
    }
}