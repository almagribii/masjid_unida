package com.example.masjid_annur.adapters

import android.view.InflateException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.masjid_annur.R
import com.example.masjid_annur.api.Ayat
import com.example.masjid_annur.api.JuzApi
import com.example.masjid_annur.api.ResponeKegiatan

class Activity2Adapter (
    private val items : MutableList<ResponeKegiatan>) : RecyclerView.Adapter<Activity2Adapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val textItem : TextView = view.findViewById(R.id.tv_kegiatan_title)
    val dateItem : TextView = view.findViewById(R.id.tv_kegiatan_tanggal)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Activity2Adapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_kegiatann, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Activity2Adapter.ViewHolder, position: Int) {
       val kegiatan = items[position]
        holder.textItem.text = kegiatan.title
        holder.dateItem.text = kegiatan.date

    }

    override fun getItemCount() = items.size

    fun updateData(newItems: List<ResponeKegiatan>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()}
}
