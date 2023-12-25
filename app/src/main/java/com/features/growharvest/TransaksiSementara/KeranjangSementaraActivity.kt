package com.features.growharvest.TransaksiSementara

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.features.growharvest.NavigationBar.NavbarMainActivity
import com.features.growharvest.R
import com.google.gson.Gson

class KeranjangSementaraActivity : AppCompatActivity() {

    private lateinit var recyclerViewCart: RecyclerView
    private lateinit var textCartTotal: TextView
    private lateinit var btnBackHome: Button
    private lateinit var btnAddProduk: ImageButton
    private lateinit var btnSelesai: Button
    private val cartList = mutableListOf<KeranjangSementara>()
    private lateinit var keranjangAdapter: KeranjangSementaraAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi_keranjang)

        recyclerViewCart = findViewById(R.id.recyclerView2)
        textCartTotal = findViewById(R.id.txtTotalHarga)
        btnBackHome = findViewById(R.id.btnBackHome)
        btnAddProduk = findViewById(R.id.btnAddProduct)
        btnSelesai = findViewById(R.id.btnSelesai)

        keranjangAdapter = KeranjangSementaraAdapter(cartList)
        recyclerViewCart.layoutManager = LinearLayoutManager(this)
        recyclerViewCart.adapter = keranjangAdapter

        updateTotal()

        btnBackHome.setOnClickListener{
            val intent = Intent(this, NavbarMainActivity::class.java)
            startActivity(intent)
        }
        btnAddProduk.setOnClickListener{
            val intent = Intent(this, ProdukSementaraActivity::class.java)
            startActivity(intent)
        }
        btnSelesai.setOnClickListener {
            // Simpan data keranjang ke SharedPreferences
            saveCartToSharedPreferences(cartList)

            // Tambahkan logika lain yang diperlukan setelah menekan tombol selesai
        }

    }

    private fun saveCartToSharedPreferences(cartList: List<KeranjangSementara>) {
        val sharedPreferences: SharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        // Convert list of Product objects to JSON using Gson or another serialization method
        // For simplicity, let's assume you have a function convertListToJson() that converts the list to JSON
        val cartJson = convertListToJson(cartList)

        editor.putString("cart", cartJson)
        editor.apply()
    }

    private fun convertListToJson(cartList: List<KeranjangSementara>): String {
        val gson = Gson()
        return gson.toJson(cartList)
    }

    private fun updateTotal() {
        val total = cartList.sumOf { it.harga_produk }
        textCartTotal.text = "Total: $$total"
    }
}