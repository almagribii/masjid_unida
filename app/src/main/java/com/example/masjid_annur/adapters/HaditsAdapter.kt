package com.example.masjid_annur.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.masjid_annur.R
import com.example.masjid_annur.activity.HaditsActivity
import com.example.masjid_annur.api.Hadith
import com.example.masjid_annur.api.JuzApi

class HaditsAdapter(
    private val haditsList: MutableList<Hadith>,
    private val onItemClick: (Hadith) -> Unit
):RecyclerView.Adapter<HaditsAdapter.HaditsViewHolder>() {

    class HaditsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvNumber: TextView = view.findViewById(R.id.numberTextHadits)
        val tvJudul: TextView = view.findViewById(R.id.namaHaditsText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HaditsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hadits, parent, false)
        return HaditsViewHolder(view)
    }

    override fun getItemCount(): Int = haditsList.size

    override fun onBindViewHolder(holder: HaditsViewHolder, position: Int) {
        val hadits = haditsList[position]
        holder.tvNumber.text = hadits.no
        holder.tvJudul.text = hadits.judul

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Klik: ${hadits.no}", Toast.LENGTH_SHORT).show()
            onItemClick(hadits)
        }
    }

    fun updateData(newList: List<Hadith>) {
        haditsList.clear()
        haditsList.addAll(newList)
        notifyDataSetChanged()
    }
}