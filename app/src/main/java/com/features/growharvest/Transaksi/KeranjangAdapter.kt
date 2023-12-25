package com.features.growharvest.Transaksi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.features.growharvest.R

class KeranjangAdapter(private val context: Context, private val keranjangList: List<Produk>)
    : RecyclerView.Adapter<KeranjangAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val isiItem: TextView = itemView.findViewById(R.id.isiItem)
        val isiHarga: TextView = itemView.findViewById(R.id.isiHarga)
        val isiKuantitas: TextView = itemView.findViewById(R.id.isiKuantitas)
        val isiTotal: TextView = itemView.findViewById(R.id.isiTotal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_keranjang, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = keranjangList[position]

        // Mengisi data ke tampilan sesuai dengan posisi item
        holder.isiItem.text = product.nama_produk
        holder.isiHarga.text = "Rp ${product.harga_produk}"
        holder.isiKuantitas.text = "${product.jumlah}"

        // Menghitung total harga untuk item tertentu
        val totalHarga = product.harga_produk * product.jumlah
        holder.isiTotal.text = "Rp $totalHarga"
    }

    override fun getItemCount(): Int {
        return keranjangList.size
    }
}

