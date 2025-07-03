package com.example.masjid_annur.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.masjid_annur.R
import com.example.masjid_annur.adapters.ClientAsynkTask
import com.example.masjid_annur.adapters.DaftarKota
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANOTATION")
class JadwalSholatActivity : AppCompatActivity(){
    private lateinit var lisDaftarKota: MutableList<DaftarKota>
    private lateinit var mDaftarKotaAdapter: ArrayAdapter<DaftarKota>
    private var greetImg: ImageView? = null
    private var greetText: TextView? = null
//    private lateinit var btnBack : LinearLayout

    private lateinit var spinnerKota: Spinner
    private lateinit var tvTanggal: TextView
    private lateinit var tvShubuh: TextView
    private lateinit var tvDzuhur: TextView
    private lateinit var tvAshar: TextView
    private lateinit var tvMaghrib: TextView
    private lateinit var tvIsya: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_jadwal_sholat)

        greetImg = findViewById(R.id.greeting_img)
        greetText = findViewById(R.id.greeting_text)
//        btnBack = findViewById(R.id.btnBack)
        spinnerKota = findViewById(R.id.kota)
        tvTanggal = findViewById(R.id.tv_tanggal)
        tvShubuh = findViewById(R.id.tv_shubuh)
        tvDzuhur = findViewById(R.id.tv_dzuhur)
        tvAshar = findViewById(R.id.tv_ashar)
        tvMaghrib = findViewById(R.id.tv_maghrib)
        tvIsya = findViewById(R.id.tv_isya)

        lisDaftarKota = ArrayList()
        mDaftarKotaAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lisDaftarKota)
        mDaftarKotaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerKota.adapter = mDaftarKotaAdapter

        spinnerKota.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Do nothing
            }
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long){
                val selectedKota = mDaftarKotaAdapter.getItem(position)
                if (selectedKota != null && selectedKota.id != null) {
                    loadJadwal(selectedKota.id!!) // ID kota sekarang String
                }
            }
        }

        loadKota()
        greeting()

//        btnBack.setOnClickListener {
//            finish()
//        }
    }

    @SuppressLint("SetTextI18n")
    private fun greeting(){
        val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        if (currentHour >= 0 && currentHour < 11) { // 00:00 - 10:59
            greetText?.text = "Selamat Pagi, Sudah Sholat Shubuh?"
            greetImg?.setImageResource(R.drawable.ic_activity)
        } else if (currentHour >= 11 && currentHour < 15){ // 11:00 - 14:59
            greetText?.text = "Selamat Siang, Sudah Sholat Dzuhur?"
            greetImg?.setImageResource(R.drawable.ic_activity)
        } else if (currentHour >= 15 && currentHour < 18) { // 15:00 - 17:59
            greetText?.text = "Selamat Sore, Sudah Sholat Ashar?"
            greetImg?.setImageResource(R.drawable.ic_copy)
        } else { // 18:00 - 23:59
            greetText?.text = "Selamat Malam, Sudah Sholat Maghrib?\nJangan lupa sholat Isya!"
            greetText?.setTextColor(Color.BLACK)
            greetImg?.setImageResource(R.drawable.contoh)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun loadJadwal (idKota: String) { // Ubah tipe parameter menjadi String
        try {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) + 1 // Bulan dimulai dari 0
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Format URL sesuai API baru: /v2/sholat/jadwal/{id_kota}/{tahun}/{bulan}/{tanggal}
            val url = "https://api.myquran.com/v2/sholat/jadwal/$idKota/$year/$month/$day"
            Log.d("JadwalSholatActivity", "Jadwal URL: $url") // Log URL untuk debugging

            val task = ClientAsynkTask(this, object : ClientAsynkTask.OnPostExecuteListener {
                override fun onPostExecute(result: String?) {
                    if (result == null) {
                        Log.e("JadwalSholatActivity", "Failed to fetch Jadwal data: result is null")
                        Toast.makeText(this@JadwalSholatActivity, "Gagal memuat jadwal sholat. Periksa koneksi internet Anda.", Toast.LENGTH_LONG).show()
                        resetJadwalTextViews() // Fungsi untuk mengosongkan teks jadwal
                        return
                    }

                    Log.d("Jadwal Data Raw", result)
                    try {
                        val jsonObj = JSONObject(result)
                        val status = jsonObj.getBoolean("status")
                        if (!status) {
                            val message = jsonObj.getString("message")
                            Log.e("JadwalSholatActivity", "API error for Jadwal: $message")
                            Toast.makeText(this@JadwalSholatActivity, "Error API jadwal: $message", Toast.LENGTH_LONG).show()
                            resetJadwalTextViews()
                            return
                        }

                        val dataObj: JSONObject = jsonObj.getJSONObject("data")
                        val jadwalObj: JSONObject = dataObj.getJSONObject("jadwal")

                        tvTanggal.text = jadwalObj.getString("tanggal")
                        tvShubuh.text = jadwalObj.getString("subuh")
                        tvDzuhur.text = jadwalObj.getString("dzuhur")
                        tvAshar.text = jadwalObj.getString("ashar")
                        tvMaghrib.text = jadwalObj.getString("maghrib")
                        tvIsya.text = jadwalObj.getString("isya")

                        Log.d("dataJadwal", jadwalObj.toString())
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        Log.e("JadwalSholatActivity", "JSON Parsing error in loadJadwal: ${e.message}")
                        Toast.makeText(this@JadwalSholatActivity, "Gagal mengurai data jadwal. Data tidak valid.", Toast.LENGTH_LONG).show()
                        resetJadwalTextViews()
                    }
                }
            })
            task.execute(url)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("JadwalSholatActivity", "Error in loadJadwal: ${e.message}")
            Toast.makeText(this@JadwalSholatActivity, "Terjadi kesalahan saat meminta jadwal.", Toast.LENGTH_LONG).show()
        }
    }

    private fun loadKota() {
        try {
            // URL API untuk daftar kota sesuai dokumentasi baru
            val url = "https://api.myquran.com/v2/sholat/kota/semua"
            Log.d("JadwalSholatActivity", "Kota URL: $url") // Log URL untuk debugging

            val task = ClientAsynkTask(this, object : ClientAsynkTask.OnPostExecuteListener {
                override fun onPostExecute(result: String?) {
                    if (result == null) {
                        Log.e("JadwalSholatActivity", "Failed to fetch Kota data: result is null")
                        Toast.makeText(this@JadwalSholatActivity, "Gagal memuat daftar kota. Periksa koneksi internet Anda.", Toast.LENGTH_LONG).show()
                        return
                    }

                    Log.d("KotaData Raw", result)
                    try {
                        val jsonObj = JSONObject(result)
                        val status = jsonObj.getBoolean("status")
                        if (!status) {
                            val message = jsonObj.getString("message")
                            Log.e("JadwalSholatActivity", "API error for Kota: $message")
                            Toast.makeText(this@JadwalSholatActivity, "Error API kota: $message", Toast.LENGTH_LONG).show()
                            return
                        }

                        val jsonArray: JSONArray = jsonObj.getJSONArray("data") // Data kota ada di dalam array "data"
                        lisDaftarKota.clear() // Bersihkan daftar yang ada sebelum menambahkan data baru

                        var daftarKota: DaftarKota?
                        for(i: Int in 0 until jsonArray.length()){
                            val obj: JSONObject = jsonArray.getJSONObject(i)
                            daftarKota = DaftarKota()
                            daftarKota.id = obj.getString("id") // Ambil ID sebagai String
                            daftarKota.nama = obj.getString("lokasi") // Ambil nama dari field "lokasi"
                            lisDaftarKota.add(daftarKota)
                        }
                        mDaftarKotaAdapter.notifyDataSetChanged()

                        // Opsional: Pilih kota Ponorogo secara otomatis jika ada
                        val ponorogoIndex = lisDaftarKota.indexOfFirst { it.nama == "Kab. Ponorogo" }
                        if (ponorogoIndex != -1) {
                            spinnerKota.setSelection(ponorogoIndex)
                        } else {
                            // Jika Ponorogo tidak ditemukan, pilih item pertama atau tampilkan pesan
                            if (lisDaftarKota.isNotEmpty()) {
                                spinnerKota.setSelection(0)
                            } else {
                                Toast.makeText(this@JadwalSholatActivity, "Tidak ada data kota ditemukan.", Toast.LENGTH_LONG).show()
                            }
                        }

                    } catch (e: JSONException) {
                        e.printStackTrace()
                        Log.e("JadwalSholatActivity", "JSON Parsing error in loadKota: ${e.message}")
                        Toast.makeText(this@JadwalSholatActivity, "Gagal mengurai data kota. Data tidak valid.", Toast.LENGTH_LONG).show()
                    }
                }
            })
            task.execute(url)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("JadwalSholatActivity", "Error in loadKota: ${e.message}")
            Toast.makeText(this@JadwalSholatActivity, "Terjadi kesalahan saat meminta daftar kota.", Toast.LENGTH_LONG).show()
        }
    }

    // Fungsi helper untuk mengosongkan TextViews jadwal
    private fun resetJadwalTextViews() {
        tvTanggal.text = "Memuat..."
        tvShubuh.text = "--:--"
        tvDzuhur.text = "--:--"
        tvAshar.text = "--:--"
        tvMaghrib.text = "--:--"
        tvIsya.text = "--:--"
    }
}