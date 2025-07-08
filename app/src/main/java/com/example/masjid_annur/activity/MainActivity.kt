package com.example.masjid_annur.activity

import HomeFragment
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.masjid_annur.R
import com.example.masjid_annur.fragment.ActivityFragment
import com.example.masjid_annur.fragment.GaleryFragment
import com.example.masjid_annur.fragment.ProfileFragment
import com.example.masjid_annur.ui.balance.SaldoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)


        // this is for default of fragment so when enter main activity this is firstly apear on your screen for default fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .commit()

        bottomNav.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.home -> HomeFragment()
                R.id.activity -> ActivityFragment()
                R.id.news -> SaldoFragment()
                R.id.profile -> ProfileFragment()
                else -> null
            }

            fragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, it)
                    .commit()
                true // ✅ return true when handled
            } ?: false
        }
    }
}