package com.example.masjid_annur.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.authentication.RetrofitKegiatan
import com.example.masjid_annur.R
import com.example.masjid_annur.adapters.Activity2Adapter
import com.example.masjid_annur.api.ResponeKegiatan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var noResult: View
    private lateinit var kegiatanAdapter: Activity2Adapter
    private lateinit var swipe : SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipe = view.findViewById(R.id.swipeRefreshLayout)
        recyclerView = view.findViewById(R.id.rv_kegiatan)
        noResult = view.findViewById(R.id.lyt_no_result)
        kegiatanAdapter = Activity2Adapter(mutableListOf())
        recyclerView.adapter = kegiatanAdapter // Penting! Set adapter setelah diinisialisasi

        val llm = LinearLayoutManager(context).apply {
            stackFromEnd = true
            reverseLayout = true
        }
        recyclerView.layoutManager = llm
        // Sembunyikan keduanya di awal, akan diatur visibilitasnya di onResponse/onFailure
        recyclerView.visibility = View.GONE
        noResult.visibility = View.GONE
        // Jika punya ProgressBar, bisa ditampilkan di sini
        swipe.setOnRefreshListener{
            fetchKegiatan()
        }
        fetchKegiatan()
    }

    private fun fetchKegiatan() {
        // Show a loading indicator or hide both until data is fetched
        // If you have a loading spinner, show it here.
        noResult.visibility = View.GONE // Hide no result while fetching

        RetrofitKegiatan.instance.getKegiatanApi()
            .enqueue(object : Callback<List<ResponeKegiatan>> {
                override fun onResponse(
                    call: Call<List<ResponeKegiatan>>, // Renamed p0 to call for clarity
                    response: Response<List<ResponeKegiatan>> // Renamed p1 to response for clarity
                ) {
                    if (response.isSuccessful) {
                        val kegiatan = response.body()
                        if (!kegiatan.isNullOrEmpty()) {
                            // Data received and not empty
                            kegiatanAdapter.updateData(kegiatan)
                            recyclerView.visibility = View.VISIBLE
                            noResult.visibility = View.GONE
                            swipe.isRefreshing = false
                        } else {
                            // API call successful, but the list is empty
                            kegiatanAdapter.updateData(emptyList()) // Clear previous data if any
                            recyclerView.visibility = View.GONE
                            noResult.visibility = View.VISIBLE
                        }
                    } else {
                        // API call not successful (e.g., 404, 500 status code)
                        Log.e("Kegiatan", "Error: ${response.code()} - ${response.message()}")
                        recyclerView.visibility = View.GONE
                        noResult.visibility = View.VISIBLE
                    }
                }

                override fun onFailure(call: Call<List<ResponeKegiatan>>, t: Throwable) { // Renamed p0, p1 to call, t for clarity
                    // Network error or other unexpected issues
                    Log.e("Kegiatan", "Failed to fetch data: ${t.message}", t) // Log full error details
                    recyclerView.visibility = View.GONE
                    noResult.visibility = View.VISIBLE
                    swipe.isRefreshing = false
                }
            })
    }

    
}