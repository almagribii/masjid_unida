import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.masjid_annur.R
import com.example.masjid_annur.activity.QuranActivity
import com.example.masjid_annur.adapters.Activity2Adapter
import com.example.masjid_annur.adapters.ActivityAdapter

class HomeFragment : Fragment() {

    private lateinit var btnQuran: LinearLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var adapter: ActivityAdapter
    private lateinit var adapter2 : Activity2Adapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var layoutManager2: LinearLayoutManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        btnQuran = view.findViewById(R.id.btnQuran)

        btnQuran?.setOnClickListener {
            val intent = Intent(activity, QuranActivity::class.java)
            startActivity(intent)
        }
    return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView2 = view.findViewById(R.id.recyclerView2)
        recyclerView = view.findViewById(R.id.recyclerView)
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        layoutManager2 = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView2.layoutManager = layoutManager2
//        recyclerView2.layoutManager = NoScrollLinearLayoutManager(context)
//        (recyclerView2.layoutManager as NoScrollLinearLayoutManager).disableScrolling()

        val data = List(20) { "Pengajian Ahad Pagi ${it + 1}" }
        val data2 = List(20) { "Pengajian Ahad Pagi ${it + 1}" }

        adapter = ActivityAdapter(data)
        recyclerView.adapter = adapter
        adapter2 = Activity2Adapter(data2)
        recyclerView2.adapter = adapter2

    }
//class NoScrollLinearLayoutManager(context: Context?) : LinearLayoutManager(context) {
//    private var scrollable = true
//
//    fun enableScrolling() {
//        scrollable = true
//    }
//
//    fun disableScrolling() {
//        scrollable = false
//    }
//
//    override fun canScrollVertically() =
//        super.canScrollVertically() && scrollable
//
//
//    override fun canScrollHorizontally() =
//        super.canScrollVertically()
//
//                && scrollable
//}

}
