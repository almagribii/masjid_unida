package com.example.masjid_annur.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MultiAutoCompleteTextView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.masjid_annur.R
import com.example.masjid_annur.api.Ayat
import com.example.masjid_annur.api.JuzApi
import com.example.masjid_annur.api.SurahRespone


class NoAyatAdapter (
    private val ayatList: MutableList<Ayat>
): RecyclerView.Adapter<NoAyatAdapter.NoAyatViewHolder> (){

    class NoAyatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvArab: TextView =view.findViewById(R.id.tvArab)
        val arabLatin: TextView = view.findViewById(R.id.arabLatin)
        val arti: TextView = view.findViewById(R.id.arti)
        val tvAyat: TextView = view.findViewById(R.id.tvnoAyat)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoAyatAdapter.NoAyatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_ayat, parent, false)
        return NoAyatViewHolder(view)
    }

    override fun getItemCount(): Int = ayatList.size

    override fun onBindViewHolder(holder: NoAyatAdapter.NoAyatViewHolder, position: Int) {
        val ayat = ayatList[position]
        holder.tvArab.text = ayat.teksArab
        holder.arabLatin.text = ayat.teksLatin
        holder.arti.text = ayat.teksIndonesia
        holder.tvAyat.text = ayat.nomorAyat.toString()
    }
    fun updateData(newList: List<Ayat>) {
        ayatList.clear()
        ayatList.addAll(newList)
        notifyDataSetChanged()
    }
}