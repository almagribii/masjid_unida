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

class Activity2Adapter (
    private val items : List<String>) : RecyclerView.Adapter<Activity2Adapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val textItem : TextView = view.findViewById(R.id.textIitem)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Activity2Adapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_activity, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Activity2Adapter.ViewHolder, position: Int) {
        holder.textItem.text = items[position]
    }

    override fun getItemCount() = items.size


}
