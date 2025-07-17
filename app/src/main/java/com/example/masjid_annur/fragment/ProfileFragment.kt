package com.example.masjid_annur.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.Fragment
import com.example.masjid_annur.R
import com.example.masjid_annur.activity.AboutDeveloperActivity
import com.example.masjid_annur.activity.BantuanActivity
import com.example.masjid_annur.activity.FAQActivity
import com.example.masjid_annur.activity.HubungiKamiActivity
import com.example.masjid_annur.activity.SyaratDanKetentuanActivity
import com.example.masjid_annur.activity.TentangAplikasiActivity


class ProfileFragment : Fragment() {
    private lateinit var btnBantuan: TextView
    private lateinit var btnHk: TextView
    private lateinit var btnTa: TextView
    private lateinit var btnSk: TextView
    private lateinit var btnFaq: TextView
    private lateinit var btnTtg: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        btnBantuan = view.findViewById(R.id.tvBantuan)
        btnHk = view.findViewById(R.id.tvHubungiKami)
        btnTa = view.findViewById(R.id.tvTentangAplikasi)
        btnSk = view.findViewById(R.id.tvSyaratKetentuan)
        btnFaq = view.findViewById(R.id.tvKeluar)
        btnTtg = view.findViewById(R.id.tvTentangDeveloper)

        btnBantuan?.setOnClickListener {
            val intent = Intent(activity, BantuanActivity::class.java)
            startActivity(intent)
        }
        btnHk?.setOnClickListener {
            val intent = Intent(activity, HubungiKamiActivity::class.java)
            startActivity(intent)
        }

        btnTa?.setOnClickListener {
            val intent = Intent(activity, TentangAplikasiActivity::class.java)
            startActivity(intent)
        }

        btnSk?.setOnClickListener {
            val intent = Intent(activity, SyaratDanKetentuanActivity::class.java)
            startActivity(intent)
        }

        btnFaq.setOnClickListener{
            exitApplication()
        }

        btnTtg.setOnClickListener{
            val intent = Intent(activity, AboutDeveloperActivity::class.java)
            startActivity(intent)
        }
        return view
    }
    private fun exitApplication() {
        // Menggunakan 'activity?.let' untuk memastikan Activity tidak null
        activity?.let { // 'it' di sini adalah referensi ke Activity induk
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // Untuk API 21 (Lollipop) ke atas
                it.finishAndRemoveTask()
            } else {
                // Untuk API di bawah 21
                it.finishAffinity()
            }
        }
    }
}