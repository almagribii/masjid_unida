package com.example.masjid_annur.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.masjid_annur.R
import com.example.masjid_annur.api.Juz



class NoJuzAdapter(
    private val juzList: MutableList<Juz>,
    private val onItemClick: (Juz) -> Unit
) : RecyclerView.Adapter<NoJuzAdapter.NoJuzViewHolder>() {

    class NoJuzViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvJuzNumber: TextView = view.findViewById(R.id.noJuz)
        val tvJuzName: TextView = view.findViewById(R.id.noJuzText)
        val tvAwalAyat: TextView = view.findViewById(R.id.awalAyatJuz)
        val noAwal: TextView = view.findViewById(R.id.noAyatt)
        val tvAkhirAyat: TextView = view.findViewById(R.id.akhirAyatJuz)
        val noAkhir: TextView = view.findViewById(R.id.noAyatAkhir)
        val strip: TextView = view.findViewById(R.id.strip)
        // Add other TextViews or views you need to bind here
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoJuzViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_juz, parent, false)
        return NoJuzViewHolder(view)
    }

    override fun getItemCount(): Int = juzList.size

    override fun onBindViewHolder(holder: NoJuzViewHolder, position: Int) {
        val juz = juzList[position]
        holder.tvJuzNumber.text = juz.number.toString() // Binding the data to views
        holder.tvJuzName.text = juz.name.toString()
        holder.tvAwalAyat.text = juz.name_start_id
        holder.noAwal.text = juz.verse_start
        holder.tvAkhirAyat.text = juz.name_end_id
        holder.noAkhir.text = juz.verse_end
        holder.strip.text = " - "

        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Klik: ${juz.name}", Toast.LENGTH_SHORT).show()
            onItemClick(juz)
        }

    }

    fun updateData(newList: List<Juz>) {
        juzList.clear()
        juzList.addAll(newList)
        notifyDataSetChanged()
    }
}
