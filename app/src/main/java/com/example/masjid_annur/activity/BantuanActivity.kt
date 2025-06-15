package com.example.masjid_annur.activity

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.masjid_annur.R

class BantuanActivity : AppCompatActivity() {
    private lateinit var btnBack: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bantuan)
        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }

//        btnBack.setOnClickListener() {
//            onBackPressed()
//        }
    }
}