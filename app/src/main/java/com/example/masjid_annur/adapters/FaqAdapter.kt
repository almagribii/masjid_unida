package com.example.masjid_annur.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.masjid_annur.R
import com.example.masjid_annur.api.FaqItem

// Contoh struktur Adapter (Kotlin)
class FaqAdapter(private val faqList: List<FaqItem>) :
    RecyclerView.Adapter<FaqAdapter.FaqViewHolder>() {

    inner class FaqViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvQuestion: TextView = itemView.findViewById(R.id.tvQuestion)
        val tvAnswer: TextView = itemView.findViewById(R.id.tvAnswer)
        val ivArrow: ImageView = itemView.findViewById(R.id.ivArrow) // Jika ada ikon panah

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = faqList[position]
                    item.isExpanded = !item.isExpanded
                    notifyItemChanged(position)
                }
            }
        }

        fun bind(faqItem: FaqItem) {
            tvQuestion.text = faqItem.question
            tvAnswer.text = faqItem.answer
            tvAnswer.visibility = if (faqItem.isExpanded) View.VISIBLE else View.GONE

            // Rotasi panah jika ada
            ivArrow.rotation = if (faqItem.isExpanded) 180f else 0f
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_faq, parent, false) // Buat item_faq.xml terpisah
        return FaqViewHolder(view)
    }

    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {
        holder.bind(faqList[position])
    }

    override fun getItemCount(): Int = faqList.size
}