package com.example.masjid_annur.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.masjid_annur.R

class HubungiKamiActivity : AppCompatActivity() {
    private lateinit var btnBack : LinearLayout
    private lateinit var btnWa : LinearLayout
    private lateinit var btnEmail: LinearLayout
    private lateinit var btnFb: LinearLayout
    private lateinit var btnIg: LinearLayout
    private lateinit var tvWa: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvFb: TextView
    private lateinit var tvIg: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hubungi_kami)

        btnWa = findViewById(R.id.ll_copy_whatsapp)
        tvWa = findViewById(R.id.tv_whatsapp_number)
        btnEmail = findViewById(R.id.ll_copy_email)
        tvEmail = findViewById(R.id.tv_email_address)
        btnFb = findViewById(R.id.ll_copy_facebook)
        tvFb = findViewById(R.id.tv_facebook_username)
        btnIg = findViewById(R.id.ll_copy_instagram)
        tvIg = findViewById(R.id.tv_instagram_username)

        btnWa.setOnClickListener{
            copy(tvWa.text.toString())
        }

        btnEmail.setOnClickListener {
            copy(tvEmail.text.toString())
        }

        btnFb.setOnClickListener{
            copy(tvFb.text.toString())
        }

        btnIg.setOnClickListener{
            copy(tvIg.text.toString())
        }

        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun copy(text: String) {
        val clipboard = this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Text", text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, "Teks Berhasil disalin", Toast.LENGTH_SHORT).show()
    }
}