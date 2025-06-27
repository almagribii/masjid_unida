package com.example.masjid_annur.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.masjid_annur.R
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        btnBantuan = view.findViewById(R.id.tvBantuan)
        btnHk = view.findViewById(R.id.tvHubungiKami)
        btnTa = view.findViewById(R.id.tvTentangAplikasi)
        btnSk = view.findViewById(R.id.tvSyaratKetentuan)
        btnFaq = view.findViewById(R.id.tvFAQ)

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
        btnFaq?.setOnClickListener {
            val intent = Intent(activity, FAQActivity::class.java)
            startActivity(intent)
        }
        return view
    }

}