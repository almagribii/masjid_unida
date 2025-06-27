package com.example.masjid_annur.activity

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.masjid_annur.R
import com.example.masjid_annur.adapters.FaqAdapter
import com.example.masjid_annur.api.FaqItem

// Kotlin
class FAQActivity : AppCompatActivity() {

    private lateinit var rvFaq: RecyclerView
    private lateinit var faqAdapter: FaqAdapter // Perubahan ada di sini

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_faq)

        rvFaq = findViewById(R.id.rvFaq)
        val btnBack: LinearLayout = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val faqList = getFaqData()

        // Inisialisasi adapter dan set ke RecyclerView
        faqAdapter = FaqAdapter(faqList) // Sekarang tipe FaqAdapter sudah cocok
        rvFaq.adapter = faqAdapter
    }

    private fun getFaqData(): List<FaqItem> {
        return listOf(
            FaqItem("Apa itu Aplikasi Masjid?", "Aplikasi Masjid adalah platform digital..."),
            FaqItem("Fitur apa saja yang tersedia?", "Fitur-fitur utama Aplikasi Masjid meliputi:..."),
            FaqItem("Bagaimana cara mengunduh Aplikasi Masjid?", "Aplikasi Masjid dapat diunduh secara gratis..."),
            FaqItem("Apakah Aplikasi Masjid memerlukan koneksi internet?", "Beberapa fitur utama seperti jadwal sholat..."),
            FaqItem("Bagaimana jika saya menemukan kesalahan pada jadwal sholat?", "Jadwal sholat dalam aplikasi biasanya disesuaikan..."),
            FaqItem("Bisakah DKM masjid saya mengelola konten?", "Ya, kami menyediakan panel admin khusus..."),
            FaqItem("Apakah data pribadi saya aman di Aplikasi Masjid?", "Kami sangat menjaga privasi pengguna...")
        )
    }
}