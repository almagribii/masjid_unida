package com.example.masjid_annur.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.masjid_annur.activity.QuranActivity
import com.example.masjid_annur.fragment.JuzFragment
import com.example.masjid_annur.fragment.SurahFragment

class QuranPagerAdapter(activity: QuranActivity): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> SurahFragment()
            1 -> JuzFragment()
            else -> throw IllegalStateException("Unexpected position: $position")
        }
    }


}