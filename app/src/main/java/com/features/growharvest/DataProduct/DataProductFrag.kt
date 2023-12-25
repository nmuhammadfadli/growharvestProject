//package com.features.growharvest.DataProduct
//
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.android.volley.Request
//import com.android.volley.Response
//import com.android.volley.VolleyError
//import com.android.volley.toolbox.JsonArrayRequest
//import com.android.volley.toolbox.Volley
//import com.features.growharvest.R
//import com.features.growharvest.Transaksi.DaftarProdukAdapter
//import org.json.JSONException
//
//class DataProductFrag : Fragment() {
//
//    private val productList = ArrayList<DataProduct>()
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var dataProductAdapter: DataProductAdapter
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_data_produk, container, false)
//
//        recyclerView = view.findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())
//
//        dataProductAdapter = DataProductAdapter(requireContext())
//        recyclerView.adapter = dataProductAdapter
//
//        val apiUrl = "https://growharvest.my.id/API/produk.php"
//
//        // Menggunakan Volley untuk mengambil data JSON
//        val requestQueue = Volley.newRequestQueue(requireContext())
//
//        val jsonArrayRequest = JsonArrayRequest(
//            Request.Method.GET, apiUrl, null,
//            Response.Listener { response ->
//                // Mengambil data produk dari JSON response
//                try {
//                    for (i in 0 until response.length()) {
//                        val productJson = response.getJSONObject(i)
//                        val product = DataProduct(
//                            productJson.getString("id_produk"),
//                            productJson.getString("nama_produk"),
//                            productJson.getInt("harga_produk"),
//                            productJson.getInt("stok_produk"),
//                            productJson.getString("deskripsi"),
//                            productJson.getString("gambar_produk")
//                        )
//                        productList.add(product)
//                    }
//                    // Memberitahu adapter bahwa dataset telah berubah
//                    dataProductAdapter.notifyDataSetChanged()
//                } catch (e: JSONException) {
//                    e.printStackTrace()
//                }
//            },
//            Response.ErrorListener { error ->
//                // Menangani kesalahan
//                error.printStackTrace()
//                if (error is VolleyError) {
//                    val errorMessage = error.message
//                    Log.e("VolleyError", errorMessage ?: "Unknown VolleyError")
//                }
//            }
//        )
//        // Menambahkan request ke queue
//        requestQueue.add(jsonArrayRequest)
//
//        return view
//    }
//}
//
//
