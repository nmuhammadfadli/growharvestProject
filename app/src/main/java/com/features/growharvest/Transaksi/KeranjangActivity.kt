package com.features.growharvest.Transaksi
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.features.growharvest.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class KeranjangActivity : AppCompatActivity() {

    private val keranjangList: MutableList<Produk> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnSelesai: Button
    private lateinit var btnAddProduct: ImageButton
    private lateinit var txtTotalHarga: TextView
    private lateinit var daftarProdukAdapter: DaftarProdukAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi_keranjang)
        txtTotalHarga = findViewById(R.id.txtTotalHarga)
        btnAddProduct = findViewById(R.id.btnAddProduct)
        btnSelesai = findViewById(R.id.btnSelesai)
        recyclerView = findViewById(R.id.recyclerView2)
        recyclerView.layoutManager = LinearLayoutManager(this)

        daftarProdukAdapter = DaftarProdukAdapter(this, keranjangList) { _, _ -> }
        recyclerView.adapter = daftarProdukAdapter

        loadKeranjangFromPreferences()
        displayKeranjang()

        btnAddProduct.setOnClickListener {
            val intent = Intent(this, DaftarProdukActivity::class.java)
            startActivityForResult(intent, 1)
        }

        // Implementasi tombol untuk melakukan pembayaran
        btnSelesai.setOnClickListener {
            // Lakukan logika pembayaran atau pindah ke activity pembayaran
            // ...

            // Setelah pembayaran berhasil, hapus keranjang
            keranjangList.clear()
            saveKeranjangToPreferences()
            displayKeranjang()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null && data.hasExtra("keranjangList")) {
                val keranjangFromIntent = data.getSerializableExtra("keranjangList") as ArrayList<*>
                keranjangList.clear()
                keranjangList.addAll(keranjangFromIntent.filterIsInstance<Produk>())
                daftarProdukAdapter.notifyDataSetChanged()
                saveKeranjangToPreferences()
                displayKeranjang()
            }
        }
    }

    private fun displayKeranjang() {
        val totalHarga = keranjangList.sumBy { it.harga_produk }
        txtTotalHarga.text = "Total Harga: $totalHarga"
    }

    private fun saveKeranjangToPreferences() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("KeranjangPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val keranjangJson = gson.toJson(keranjangList)
        editor.putString("keranjang", keranjangJson)
        editor.apply()
    }

    private fun loadKeranjangFromPreferences() {
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("KeranjangPrefs", Context.MODE_PRIVATE)
        val gson = Gson()
        val keranjangJson = sharedPreferences.getString("keranjang", null)
        val type = object : TypeToken<List<Produk>>() {}.type
        keranjangList.clear()
        keranjangList.addAll(gson.fromJson(keranjangJson, type) ?: emptyList())
    }
}