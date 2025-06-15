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
import com.example.masjid_annur.activity.QuranActivity


class ProfileFragment : Fragment() {
private lateinit var btnBantuan : TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        btnBantuan = view.findViewById(R.id.tvBantuan)

        btnBantuan?.setOnClickListener {
            val intent = Intent(activity, BantuanActivity::class.java)
            startActivity(intent)
        }
        return view
    }

}