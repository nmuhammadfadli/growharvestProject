package com.features.growharvest.TransaksiSementara
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.features.growharvest.R

class ProdukSementaraActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val productList = listOf(
        ProdukSementara("Product 1", 10000, R.drawable.product1, 10),
        ProdukSementara("Product 2", 15000, R.drawable.product2, 10),
        ProdukSementara("Product 3", 20000, R.drawable.product3, 10)
    )
    private val cartList = mutableListOf<ProdukSementara>()
    private lateinit var productAdapter: ProdukSementaraAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_barang)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        productAdapter = ProdukSementaraAdapter(productList) { product ->
            addToCart(product)
        }
        recyclerView.adapter = productAdapter
    }

    private fun addToCart(product: ProdukSementara) {
        cartList.add(product)
        updateCartRecyclerView()
    }

    private fun updateCartRecyclerView() {
        productAdapter.setCartList(cartList)
    }
}
