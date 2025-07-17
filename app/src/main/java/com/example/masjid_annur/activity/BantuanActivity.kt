package com.example.masjid_annur.activity

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.masjid_annur.R

class BantuanActivity : AppCompatActivity() {
    private lateinit var btnBack: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bantuan2)
        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }

        val webView : WebView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSe5Xw9Kv9l_-Wgd4_9x5URdnYE1ccPbKEZ1ubENdnGXP-Aw9w/viewform?usp=dialog")

//        btnBack.setOnClickListener() {
//            onBackPressed()
//        }
    }
}