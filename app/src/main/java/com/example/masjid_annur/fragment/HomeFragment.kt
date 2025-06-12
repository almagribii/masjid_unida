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
//    private var currentPosition = 0
//    private val handler = Handler(Looper.getMainLooper())

//    private val autoScroll = object : Runnable {
//        override fun run() {
//            if (!::adapter.isInitialized || adapter.itemCount == 0) return
//
//            currentPosition = (currentPosition + 1) % adapter.itemCount
//            recyclerView.smoothScrollToPosition(currentPosition)
//
//            handler.postDelayed(this, 8000) // tiap 8 detik scroll
//        }
//    }

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

        val data = List(20) { "Item ${it + 1}" }
        val data2 = List(20) { "Item ${it + 1}" }

        adapter = ActivityAdapter(data)
        recyclerView.adapter = adapter
        adapter2 = Activity2Adapter(data2)
        recyclerView2.adapter = adapter2

//        handler.post(autoScroll) // mulai auto scroll
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        handler.removeCallbacks(autoScroll) // jangan lupa berhentiin handler
//    }


}
