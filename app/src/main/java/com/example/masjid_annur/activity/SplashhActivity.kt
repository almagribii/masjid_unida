package com.example.masjid_annur.activity


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.masjid_annur.R

class SplashhActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000 // Durasi total splash screen
    private lateinit var logoSplash: LinearLayout // Deklarasi ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splashh)

        logoSplash = findViewById(R.id.logo_splash) // Inisialisasi ImageView

        // Load animasi
        val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_scale)
        logoSplash.startAnimation(scaleAnimation) // Terapkan animasi ke logo

        // Handler untuk memulai MainActivity setelah durasi splash screen
        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this@SplashhActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }, SPLASH_TIME_OUT)
    }
}