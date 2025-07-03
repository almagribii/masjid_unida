import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import com.example.masjid_annur.adapters.ActivityAdapter
import com.example.masjid_annur.api.DoaAcakRespone
import com.example.masjid_annur.api.RetrofitJuz
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var btnQuran: LinearLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var adapter: ActivityAdapter
    private lateinit var adapter2: Activity2Adapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var layoutManager2: LinearLayoutManager
    private lateinit var btnTasbih: LinearLayout
    private lateinit var btnJadwal: LinearLayout
    private lateinit var btnKiblat: LinearLayout
    private lateinit var btnTanya: LinearLayout
    private lateinit var listDoaAcak: DoaAcakRespone
    private lateinit var tvdoaArab: TextView
    private lateinit var tvdoaIndo: TextView
    private lateinit var tvdoaJudul: TextView
    private lateinit var tvdoaSource: TextView


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

//        recyclerView2 = view.findViewById(R.id.recyclerView2)
        recyclerView = view.findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
//        layoutManager2 = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
//        recyclerView2.layoutManager = layoutManager2
//        recyclerView2.layoutManager = NoScrollLinearLayoutManager(context)
//        (recyclerView2.layoutManager as NoScrollLinearLayoutManager).disableScrolling()

        val datas = List(20) { "Pengajian Ahad Pagi ${it + 1}" }

        adapter = ActivityAdapter(datas)
        recyclerView.adapter = adapter
        ambilDoa()


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
}
