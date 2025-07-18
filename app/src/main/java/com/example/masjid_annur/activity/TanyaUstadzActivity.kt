package com.example.masjid_annur.activity

import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.ScrollView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.masjid_annur.R
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.content // PASTIKAN IMPORT INI ADA
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TanyaUstadzActivity : AppCompatActivity() {

    private lateinit var btnBack: LinearLayout
    private lateinit var etQuestion: EditText
    private lateinit var btnAsk: ImageButton
    private lateinit var generativeModel: GenerativeModel
    private lateinit var chat: Chat
    private lateinit var llChatContainer: LinearLayout
    private lateinit var scrollView: ScrollView
    private lateinit var progressBar: ProgressBar

    // SANGAT PENTING: Jangan menyimpan API Key langsung seperti ini di aplikasi yang akan di-release!
    // Gunakan BuildConfig, local.properties, atau ambil dari backend.
    private val API_KEY = "AIzaSyDW1VZe5D6vf9hyzhXm5B8PRj4pEk0YwsY" // Ganti dengan API Key Anda

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tanya_ustad)

        // Inisialisasi komponen UI
        btnBack = findViewById(R.id.btnBack)
        etQuestion = findViewById(R.id.etQuestion)
        btnAsk = findViewById(R.id.btnAsk)
        llChatContainer = findViewById(R.id.llChatContainer)
        scrollView = findViewById(R.id.scrollView)
        progressBar = findViewById(R.id.progressBar)

        // Inisialisasi GenerativeModel dengan system instruction
        generativeModel = GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = API_KEY,
            systemInstruction = content {
                text("Berperanlah sebagai ustadz dengan nama Brucad Al Magribi yang bijak dan tegas. Berikan jawaban yang padat, jelas, dan lugas sesuai tuntunan Al-Qur'an dan Sunnah. Setiap respons harus ringkas, tanpa basa-basi, namun tetap memberikan pencerahan yang kuat. jangan bilang kamu adalah program komputer")
            }
        )

        // Inisialisasi objek Chat dari GenerativeModel
        chat = generativeModel.startChat()

        // Set OnClickListener untuk tombol kembali
        btnBack.setOnClickListener {
            finish()
        }

        // Set OnClickListener untuk tombol "Kirim Pertanyaan"
        btnAsk.setOnClickListener {
            val question = etQuestion.text.toString().trim()
            if (question.isNotEmpty()) {
                addMessageToChat(question, true) // Tambahkan pesan pengguna
                etQuestion.text.clear() // Kosongkan input setelah dikirim
                sendPrompt(question)
            } else {
                etQuestion.error = "Pertanyaan tidak boleh kosong!"
            }
        }
    }

    // Fungsi untuk menambahkan pesan ke tampilan chat
    private fun addMessageToChat(message: String, isUser: Boolean) {
        val layoutInflater = LayoutInflater.from(this)
        val messageView = layoutInflater.inflate(R.layout.item_chat_message, llChatContainer, false) as LinearLayout
        val tvMessage = messageView.findViewById<TextView>(R.id.tvMessage)

        tvMessage.text = message

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        // Margin bawah antar pesan
        params.setMargins(0, 0, 0, resources.getDimensionPixelSize(R.dimen.chat_message_margin_bottom))

        if (isUser) {
            // Pesan pengguna (kanan)
            messageView.background = getDrawable(R.drawable.bg_message_user)
            params.gravity = Gravity.END
            tvMessage.setTextColor(resources.getColor(android.R.color.white, theme)) // Teks putih untuk pesan pengguna
            // Margin kiri agar tidak terlalu menempel ke pinggir
            params.marginStart = resources.getDimensionPixelSize(R.dimen.chat_message_margin_side)
        } else {
            // Pesan Ustadz (kiri)
            messageView.background = getDrawable(R.drawable.bg_message_ustadz)
            params.gravity = Gravity.START
            tvMessage.setTextColor(resources.getColor(android.R.color.black, theme)) // Teks hitam untuk pesan Ustadz
            // Margin kanan agar tidak terlalu menempel ke pinggir
            params.marginEnd = resources.getDimensionPixelSize(R.dimen.chat_message_margin_side)
        }

        messageView.layoutParams = params
        llChatContainer.addView(messageView)

        // Gulir ke paling bawah setelah menambahkan pesan baru
        scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_DOWN) }
    }

    // Fungsi untuk mengirim prompt ke Gemini API dan mengelola riwayat chat
    private fun sendPrompt(promptText: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                progressBar.visibility = View.VISIBLE // Tampilkan loading
                btnAsk.isEnabled = false // Nonaktifkan tombol kirim saat loading
            }
            try {
                // --- PERBAIKAN UTAMA ADA DI SINI ---
                // Buat objek Content dari String promptText
                val userContent = content { text(promptText) }
                val response = chat.sendMessage(userContent) // Gunakan objek Content di sini
                // ----------------------------------

                val generatedText = response.text

                withContext(Dispatchers.Main) {
                    if (generatedText != null) {
                        addMessageToChat(generatedText, false) // Tambahkan jawaban Ustadz
                    } else {
                        addMessageToChat("Ustadz: Tidak ada respons dari Gemini.", false)
                    }
                }
            } catch (e: Exception) {
                Log.e("TanyaUstadzActivity", "Error calling Gemini API: ${e.message}", e)
                withContext(Dispatchers.Main) {
                    addMessageToChat("Ustadz: Terjadi kesalahan: ${e.message}", false)
                }
            } finally {
                withContext(Dispatchers.Main) {
                    progressBar.visibility = View.GONE // Sembunyikan loading
                    btnAsk.isEnabled = true // Aktifkan kembali tombol kirim
                }
            }
        }
    }
}