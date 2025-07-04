// adapters/ArticleAdapter.kt (Ganti nama file jika perlu)
package com.example.masjid_annur.adapters // Pastikan ini sesuai

import android.content.Intent // Untuk Intent saat klik URL
import android.net.Uri // Untuk parsing URL
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.masjid_annur.R
// Perbarui import sesuai dengan nama data class yang baru
import com.example.masjid_annur.api.ArticleItem

// Ganti nama class dari ActivityAdapter menjadi ArticleAdapter agar lebih jelas
// Dan pastikan itu menerima List<ArticleItem>
class ArticleAdapter(private var articles: List<ArticleItem>) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    // Inner class ViewHolder (sesuai yang sudah Anda buat di jawaban sebelumnya)
    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewAuthor: TextView = itemView.findViewById(R.id.textViewAuthor)
        val textViewDate: TextView = itemView.findViewById(R.id.textViewDate)
        val textViewType: TextView = itemView.findViewById(R.id.textViewType)
        val textViewUrl: TextView = itemView.findViewById(R.id.textViewUrl) // Untuk 'Baca Selengkapnya'
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        // Pastikan item_activity2 adalah item_article (atau item_news_article)
        // karena Anda menggunakan R.id.textViewTitle dll. dari item_article.xml
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_activity2, parent, false) // <-- Perbaiki nama layout di sini
        return ArticleViewHolder(view)
    }

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.textViewTitle.text = article.title
        // Gunakan Elvis operator (?:) untuk menangani nilai null pada author
        holder.textViewAuthor.text = article.author ?: "Penulis Tidak Diketahui"
        holder.textViewDate.text = article.date
        holder.textViewType.text = article.type

        // Tambahkan onClickListener untuk 'Baca Selengkapnya' atau seluruh item
        holder.textViewUrl.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
            holder.itemView.context.startActivity(intent)
        }
        holder.itemView.setOnClickListener { // Klik pada CardView juga membuka URL
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
            holder.itemView.context.startActivity(intent)
        }
    }

    // Fungsi untuk memperbarui data di adapter
    fun updateArticles(newList: List<ArticleItem>) { // Perbaiki tipe parameter menjadi List<ArticleItem>
        this.articles = newList
        notifyDataSetChanged()
    }
}