package com.example.masjid_annur.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.masjid_annur.R
import com.example.masjid_annur.adapters.HaditsAdapter.HaditsViewHolder
import com.example.masjid_annur.api.DoaSumberRespone
import com.example.masjid_annur.api.Hadith

class DoaAdapter(
    private var doaList: List<String>
):RecyclerView.Adapter<DoaAdapter.DoaViewHolder>() {

    class DoaViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvdaftar: TextView = view.findViewById(R.id.namaDoaText)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoaAdapter.DoaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_daftar_doa, parent, false)
        return DoaViewHolder(view)    }

    override fun onBindViewHolder(holder: DoaAdapter.DoaViewHolder, position: Int) {
        val doa = doaList[position]
        holder.tvdaftar.text = doa
   }

    override fun getItemCount(): Int = doaList.size

    fun updateData(newList: List<String>) {
        this.doaList = newList
        notifyDataSetChanged()
    }
}