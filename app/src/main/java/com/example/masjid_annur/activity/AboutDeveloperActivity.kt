package com.example.masjid_annur.activity

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.masjid_annur.R

class AboutDeveloperActivity : AppCompatActivity() {
    private lateinit var btnBack: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about_developer)
        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }

        val webView : WebView = findViewById(R.id.webView2)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://my-portfolio-jade-psi-72.vercel.app/")

//        btnBack.setOnClickListener() {
//            onBackPressed()
//        }
    }
}