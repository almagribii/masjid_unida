package com.example.masjid_annur.adapters

import android.view.InflateException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.masjid_annur.R
import com.example.masjid_annur.api.Ayat
import com.example.masjid_annur.api.JuzApi
import com.example.masjid_annur.api.ResponeKegiatan
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class Activity2Adapter (
    private val items : MutableList<ResponeKegiatan>) : RecyclerView.Adapter<Activity2Adapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val textItem : TextView = view.findViewById(R.id.tv_kegiatan_title)
    val dateItem : TextView = view.findViewById(R.id.tv_kegiatan_datetime)
    val location : TextView = view.findViewById(R.id.tv_kegiatan_location)
    val speaker : TextView = view.findViewById(R.id.tv_kegiatan_speaker)
    val image : ImageView  = view.findViewById(R.id.item_image)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Activity2Adapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_kegiatan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Activity2Adapter.ViewHolder, position: Int) {
       val kegiatan = items[position]
        holder.textItem.text = kegiatan.title
        holder.dateItem.text = kegiatan.activityDateTime
        try {
            val dateTimeStringFromApi = kegiatan.activityDateTime // Contoh: "2025-07-09T13:30:00"

            // Formatter untuk mem-parse input ISO 8601 dari API
            // Perhatikan 'T' dan millisecond 'SSS' jika ada (API kita tidak pakai SSS)
            val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
            inputFormatter.timeZone = TimeZone.getTimeZone("UTC") // Asumsi data dari API adalah UTC atau tanpa zona waktu

            val date: Date? = inputFormatter.parse(dateTimeStringFromApi)

            if (date != null) {
                // Formatter untuk nama hari (contoh "Sabtu")
                val dayOfWeekFormatter = SimpleDateFormat("EEEE", Locale("id", "ID"))
                dayOfWeekFormatter.timeZone = TimeZone.getTimeZone("Asia/Jakarta") // Zona waktu Ponorogo

                // Formatter untuk tanggal dan waktu (contoh "27 Juli 2025 08:00")
                val dateTimeFormatter = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale("id", "ID"))
                dateTimeFormatter.timeZone = TimeZone.getTimeZone("Asia/Jakarta") // Zona waktu Ponorogo

                // Formatter untuk singkatan zona waktu (contoh "WIB")
                // Perlu sedikit trik karena SimpleDateFormat tidak langsung punya TextStyle.SHORT
                val zoneFormatter = SimpleDateFormat("zzz", Locale("id", "ID"))
                zoneFormatter.timeZone = TimeZone.getTimeZone("Asia/Jakarta") // Zona waktu Ponorogo

                val formattedDayOfWeek = dayOfWeekFormatter.format(date)
                val formattedDateTimePart = dateTimeFormatter.format(date)
                val formattedZoneAbbreviation = zoneFormatter.format(date)

                val finalFormattedString = "$formattedDayOfWeek, $formattedDateTimePart $formattedZoneAbbreviation"
                holder.dateItem.text = finalFormattedString
            } else {
                holder.dateItem.text = "Format tanggal tidak valid"
            }
        } catch (e: Exception) {
            holder.dateItem.text = "Error parsing tanggal"
            e.printStackTrace()
        }

        holder.speaker.text = kegiatan.speaker
        holder.location.text = kegiatan.location
        holder.image.apply {
            // Menggunakan Glide untuk memuat gambar dari URL
            Glide.with(context) // 'context' adalah konteks yang biasanya tersedia di itemView.context atau dari adapter itu sendiri
                .load(kegiatan.imageUrl) // URL gambar dari objek kegiatan
                .placeholder(R.drawable.placeholder_activity) // Gambar yang ditampilkan saat loading atau imageUrl null
                .error(R.drawable.error_image) // Gambar yang ditampilkan jika terjadi error saat memuat
                .into(this) // 'this' merujuk ke ImageView (holder.image)
        }
    }

    override fun getItemCount() = items.size

    fun updateData(newItems: List<ResponeKegiatan>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()}
}
