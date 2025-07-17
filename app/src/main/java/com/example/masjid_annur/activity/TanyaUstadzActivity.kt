package com.example.masjid_annur.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.masjid_annur.R
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.Chat // Import Chat
import com.google.ai.client.generativeai.type.content // Import content builder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TanyaUstadzActivity : AppCompatActivity() {

    private lateinit var btnBack: LinearLayout
    private lateinit var tvResult: TextView // Untuk menampilkan riwayat percakapan
    private lateinit var etQuestion: EditText
    private lateinit var btnAsk: ImageButton
    private lateinit var generativeModel: GenerativeModel
    private lateinit var chat: Chat // Deklarasi objek Chat

    // SANGAT PENTING: Jangan menyimpan API Key langsung seperti ini di aplikasi yang akan di-release!
    // Gunakan BuildConfig, local.properties, atau ambil dari backend.
    private val API_KEY = "AIzaSyCmfuSbFOpFBKmZMNZCnpgBLO7s3CpuDEg" // Ganti dengan API Key Anda

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tanya_ustad)

        // Inisialisasi komponen UI
        btnBack = findViewById(R.id.btnBack)
        tvResult = findViewById(R.id.tvResult)
        etQuestion = findViewById(R.id.etQuestion)
        btnAsk = findViewById(R.id.btnAsk)

        // Inisialisasi GenerativeModel
        generativeModel = GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = API_KEY
        )

        // Inisialisasi objek Chat dari GenerativeModel
        // Anda bisa memberikan riwayat awal jika ada (misalnya, dari penyimpanan lokal)
        chat = generativeModel.startChat() // Memulai percakapan baru

        // Set OnClickListener untuk tombol kembali
        btnBack.setOnClickListener {
            finish()
        }

        // Set OnClickListener untuk tombol "Kirim Pertanyaan"
        btnAsk.setOnClickListener {
            val question = etQuestion.text.toString().trim()
            if (question.isNotEmpty()) {
                sendPrompt(question)
                etQuestion.text.clear() // Kosongkan input setelah dikirim
                // Tampilkan pesan loading atau update UI segera
                tvResult.append("\n\nAnda: $question") // Tampilkan pertanyaan pengguna
                tvResult.append("\nUstadz: Mohon tunggu...") // Tampilkan pesan loading
            } else {
                etQuestion.error = "Pertanyaan tidak boleh kosong!"
            }
        }
    }

    // Fungsi untuk mengirim prompt ke Gemini API dan mengelola riwayat chat
    private fun sendPrompt(promptText: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                // Kirim pesan menggunakan objek chat, bukan langsung generativeModel
                val response = chat.sendMessage(promptText) // Ini yang membedakan
                val generatedText = response.text

                runOnUiThread {
                    if (generatedText != null) {
                        // Hapus pesan "Mohon tunggu..." dan ganti dengan jawaban sebenarnya
                        val currentText = tvResult.text.toString()
                        val updatedText = currentText.replace("Ustadz: Mohon tunggu...", "Ustadz: $generatedText")
                        tvResult.text = updatedText

                        // Atau jika Anda ingin selalu menambahkan ke bawah:
                        // tvResult.append("\n\nUstadz: $generatedText")

                    } else {
                        val currentText = tvResult.text.toString()
                        val updatedText = currentText.replace("Ustadz: Mohon tunggu...", "Ustadz: Tidak ada respons dari Gemini.")
                        tvResult.text = updatedText
                    }
                }
            } catch (e: Exception) {
                Log.e("TanyaUstadzActivity", "Error calling Gemini API: ${e.message}", e)
                runOnUiThread {
                    val currentText = tvResult.text.toString()
                    val updatedText = currentText.replace("Ustadz: Mohon tunggu...", "Ustadz: Terjadi kesalahan: ${e.message}")
                    tvResult.text = updatedText
                }
            }
        }
    }
}