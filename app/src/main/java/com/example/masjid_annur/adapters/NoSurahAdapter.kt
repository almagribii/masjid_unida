package com.example.masjid_annur.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.masjid_annur.R
import com.example.masjid_annur.api.JuzApi

class NoSurahAdapter (
    private val surahList: MutableList<JuzApi>,
    private val onItemClick: (JuzApi) -> Unit
):RecyclerView.Adapter<NoSurahAdapter.NoSurahViewHolder> (){

    class NoSurahViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNoSurah: TextView =view.findViewById(R.id.numberText)
        val tvSurah: TextView = view.findViewById(R.id.namaSurahText)
        val tvPlaceDown: TextView = view.findViewById(R.id.kotaText)
        val tvAyat: TextView =view.findViewById(R.id.jumlahayat)
        val tvSurahArab: TextView = view.findViewById(R.id.arabText)
        val penghalang: TextView = view.findViewById(R.id.penghalang)
        val tvAyatt: TextView = view.findViewById(R.id.tvayat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoSurahViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_surah, parent, false)
        return NoSurahViewHolder(view)
    }
    override fun getItemCount(): Int = surahList.size

    override fun onBindViewHolder(holder: NoSurahViewHolder, position: Int) {
        val surah = surahList[position]
        holder.tvNoSurah.text = surah.nomor.toString()
        holder.tvAyat.text = surah.jumlahAyat.toString()
        holder.tvPlaceDown.text= surah.tempatTurun
        holder.tvSurah.text = surah.namaLatin
        holder.tvSurahArab.text = surah.nama
        holder.penghalang.text = " | "
        holder.tvAyatt.text = " Ayat"

        holder.itemView.setOnClickListener{
            Toast.makeText(holder.itemView.context, "Klik: ${surah.namaLatin}", Toast.LENGTH_SHORT).show()
            onItemClick(surah)
        }
    }

    fun updateData(newList: List<JuzApi>) {
        surahList.clear()
        surahList.addAll(newList)
        notifyDataSetChanged()
    }
}