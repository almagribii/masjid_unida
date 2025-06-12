package com.example.masjid_annur.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.masjid_annur.R
import com.example.masjid_annur.api.Juz

import com.example.masjid_annur.api.JuzApi
import com.example.masjid_annur.api.JuzDetailResponse
import org.w3c.dom.Text
import java.security.PrivateKey

class NoJuzAdapter(
    private val juzList: MutableList<Juz>,
    private val onItemClick: (Juz) -> Unit
) : RecyclerView.Adapter<NoJuzAdapter.NoJuzViewHolder>() {

    class NoJuzViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvJuzNumber: TextView = view.findViewById(R.id.noText)
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

        holder.itemView.setOnClickListener {
            onItemClick(juz) // Handle item click
            
        }

    }

    // Method to update the data list
//    fun updateData(newList: List<Juz>) {
//        juzList.clear()
//        juzList.addAll(newList)
//        notifyDataSetChanged()
//    }
}
