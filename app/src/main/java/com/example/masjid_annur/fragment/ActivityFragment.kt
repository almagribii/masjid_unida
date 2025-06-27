package com.example.masjid_annur.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.authentication.RetrofitKegiatan
import com.example.masjid_annur.R
import com.example.masjid_annur.adapters.Activity2Adapter
import com.example.masjid_annur.api.ResponeKegiatan
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var kegiatanAdapter :  Activity2Adapter

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
        recyclerView =view.findViewById(R.id.rv_kegiatan)

        fetchKegiatan()
    }

    private fun  fetchKegiatan(){
        RetrofitKegiatan.instance.getKegiatanApi().enqueue(object : Callback<List<ResponeKegiatan>> {
            override fun onResponse(p0: Call<List<ResponeKegiatan>>, p1: Response<List<ResponeKegiatan>>) {
                if (p1.isSuccessful){

                    val kegiatan =  p1.body()
                    kegiatanAdapter.updateData(kegiatan?: emptyList())
                } else {
                    Log.e("Kegiatan", "Error : ${p1.code()}")
                }
            }

            override fun onFailure(p0: Call<List<ResponeKegiatan>>, p1: Throwable) {
                Log.e("kegiatan", "error")
            }

        })
    }
}