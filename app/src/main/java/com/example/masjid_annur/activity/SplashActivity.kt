package com.example.masjid_annur.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.masjid_annur.R
import com.example.masjid_annur.adapters.QuranPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SplashActivity : AppCompatActivity() {

    private lateinit var button: Button

    companion object {
        private const val PREF_NAME = "MyPrefs"
        private const val KEY_FIRST_RUN = "firstRun"
        private const val SPLASH_TIME_OUT: Long = 1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        val prefs: SharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val isFirstRun = prefs.getBoolean(KEY_FIRST_RUN, true) // Defaultnya true jika belum ada

        if (isFirstRun) {
            // Ini adalah pertama kali aplikasi dibuka
            Handler(Looper.getMainLooper()).postDelayed({
                // Setelah waktu splash selesai, pindah ke MainActivity
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish() // Tutup SplashActivity agar tidak bisa kembali ke sini
            }, SPLASH_TIME_OUT)

            // Setel isFirstRun menjadi false agar di kemudian hari tidak muncul lagi
            prefs.edit().putBoolean(KEY_FIRST_RUN, false).apply()
        } else {
            // Aplikasi sudah pernah dibuka sebelumnya, langsung ke MainActivity
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish() // Tutup SplashActivity
        }

    button = findViewById(R.id.btnSplash)

        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }
}