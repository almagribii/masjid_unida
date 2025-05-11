package com.example.masjid_annur.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.masjid_annur.R

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var button1: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.btAlquran)
        button1 = findViewById(R.id.btJadwalSholat)

        button.setOnClickListener{
        val intent = Intent(this, QuranActivity::class.java)
        startActivity(intent)
        }
        button1.setOnClickListener{
        val intent = Intent(this, JadwalSholatActivity::class.java)
        startActivity(intent)
        }
    }

}