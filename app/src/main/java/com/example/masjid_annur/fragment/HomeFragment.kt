import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.masjid_annur.R
import com.example.masjid_annur.activity.HaditsActivity
import com.example.masjid_annur.activity.JadwalSholatActivity
import com.example.masjid_annur.activity.DoaActivity
import com.example.masjid_annur.activity.QuranActivity
import com.example.masjid_annur.activity.TanyaUstadzActivity
import com.example.masjid_annur.adapters.Activity2Adapter
import com.example.masjid_annur.adapters.ArticleAdapter
import com.example.masjid_annur.api.ArticleApiResponse
import com.example.masjid_annur.api.DoaAcakRespone
import com.example.masjid_annur.api.RetrofitJuz
import com.example.masjid_annur.api.RetrofitNews
import kotlinx.coroutines.newSingleThreadContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var btnQuran: LinearLayout
    private lateinit var recyclerViewNews: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var btnTasbih: LinearLayout
    private lateinit var btnJadwal: LinearLayout
    private lateinit var btnKiblat: LinearLayout
    private lateinit var btnTanya: LinearLayout
    private lateinit var tvdoaArab: TextView
    private lateinit var tvdoaIndo: TextView
    private lateinit var tvdoaJudul: TextView
    private lateinit var tvdoaSource: TextView
    private lateinit var newsAdapter: ArticleAdapter
    private lateinit var cardCadangan: View
    private lateinit var progressCadangan: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        btnQuran = view.findViewById(R.id.btnQuran)
        btnTasbih = view.findViewById(R.id.btnTasbih)
        btnJadwal = view.findViewById(R.id.btnJadwal)
        btnKiblat = view.findViewById(R.id.btnKiblat)
        btnTanya = view.findViewById(R.id.btnTanya)
        tvdoaArab = view.findViewById(R.id.tvDoaArab)
        tvdoaIndo = view.findViewById(R.id.tvDoaIndo)
        tvdoaJudul = view.findViewById(R.id.tvDoaJudul)
        tvdoaSource = view.findViewById(R.id.tvDoaSource)
        cardCadangan = view.findViewById(R.id.cardCadangan)
        progressCadangan = view.findViewById(R.id.progressCardCadangan)
        btnQuran.setOnClickListener {
            val intent = Intent(activity, QuranActivity::class.java)
            startActivity(intent)
        }
        btnTanya.setOnClickListener {
            val intent = Intent(activity, TanyaUstadzActivity::class.java)
            startActivity(intent)
        }

        btnTasbih.setOnClickListener {
            val intent = Intent(activity, HaditsActivity::class.java)
            startActivity(intent)
        }

        btnJadwal.setOnClickListener {
            val intent = Intent(activity, JadwalSholatActivity::class.java)
            startActivity(intent)
        }
        btnKiblat.setOnClickListener {
            val intent = Intent(activity, DoaActivity::class.java)
            startActivity(intent)
        }
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewNews = view.findViewById(R.id.recyclerViewNews)
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewNews.layoutManager = layoutManager
        newsAdapter = ArticleAdapter(emptyList())
        recyclerViewNews.adapter = newsAdapter
        ambilDoa()
        fetchArticle()

    }

    class NoScrollLinearLayoutManager(context: Context?) : LinearLayoutManager(context) {
        private var scrollable = true

        fun enableScrolling() {
            scrollable = true
        }

        fun disableScrolling() {
            scrollable = false
        }

        override fun canScrollVertically() =
            super.canScrollVertically() && scrollable


        override fun canScrollHorizontally() =
            super.canScrollVertically()
                    && scrollable
    }


    fun ambilDoa() {
        RetrofitJuz.instance.getDoaAcak().enqueue(object : Callback<DoaAcakRespone> {
            override fun onResponse(p0: Call<DoaAcakRespone>, p1: Response<DoaAcakRespone>) {
                if (p1.isSuccessful){
                    val doaAcak = p1.body()?.data
                    tvdoaArab.text = doaAcak?.arab
                    tvdoaIndo.text = doaAcak?.indo
                    tvdoaJudul.text = doaAcak?.judul
                    tvdoaSource.text = doaAcak?.source
                }

            }
            override fun onFailure(p0: Call<DoaAcakRespone>, p1: Throwable) {
                Toast.makeText(requireContext(), "Gagal mengambil data.", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun fetchArticle(){
        // Menggunakan ArticleApiResponse yang sudah diperbaiki
        RetrofitNews.instance.getNewNews().enqueue(object : Callback<ArticleApiResponse>{
            override fun onResponse(call: Call<ArticleApiResponse>, response: Response<ArticleApiResponse>) {
                if (response.isSuccessful){
                    val apiResponse = response.body()
                    // Mengakses list 'articles' yang benar dari ArticleDataWrapper
                    apiResponse?.data?.articles?.let { articleList ->
                        newsAdapter.updateArticles(articleList) // Memanggil updateArticles dengan list yang benar
                    } ?: run {
                        Log.e("HomeFragment", "Article list is null or empty from API response.")
                        Toast.makeText(requireContext(), "Tidak ada artikel ditemukan.", Toast.LENGTH_SHORT).show()
                    }
                    cardCadangan.visibility = View.GONE
                    recyclerViewNews.visibility = View.VISIBLE
                } else {
                    Log.e("HomeFragment", "Gagal mengambil artikel: ${response.code()} - ${response.message()}")
                    Toast.makeText(requireContext(), "Gagal mengambil artikel: ${response.code()}", Toast.LENGTH_SHORT).show()
                    progressCadangan.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<ArticleApiResponse>, t: Throwable) {
                Log.e("HomeFragment", "Kesalahan jaringan saat mengambil artikel: ${t.message}", t)
                Toast.makeText(requireContext(), "Kesalahan jaringan saat mengambil artikel.", Toast.LENGTH_SHORT).show()
                progressCadangan.visibility = View.GONE
            }
        })
    }
}
