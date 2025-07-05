package com.example.masjid_annur.activity

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.BuildConfig
import com.example.masjid_annur.R
import com.google.ai.client.generativeai.GenerativeModel

class TanyaUstadzActivity : AppCompatActivity() {
    private lateinit var btnBack : LinearLayout
    private lateinit var generativeModel: GenerativeModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tanya_ustad)

        btnBack = findViewById(R.id.ivNotification)

        btnBack.setOnClickListener {
            finish()
        }
    }
}