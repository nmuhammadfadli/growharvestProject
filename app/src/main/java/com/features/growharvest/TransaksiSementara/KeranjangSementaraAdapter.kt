package com.features.growharvest.TransaksiSementara
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.features.growharvest.R

class KeranjangSementaraAdapter(private val cartList: MutableList<KeranjangSementara>) :
    RecyclerView.Adapter<KeranjangSementaraAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.isiItem)
        val priceTextView: TextView = itemView.findViewById(R.id.isiHarga)
        val quantityTextView: TextView = itemView.findViewById(R.id.isiKuantitas)
        val totalTextView: TextView = itemView.findViewById(R.id.isiTotal)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_keranjang, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = cartList[position]
        holder.nameTextView.text = product.nama_produk
        holder.priceTextView.text = "Rp. ${product.harga_produk}"
        holder.quantityTextView.text = "${product.jumlah_produk}"

        val totalHarga = product.harga_produk * product.jumlah_produk
        holder.totalTextView.text = "Rp $totalHarga"
    }

    override fun getItemCount(): Int = cartList.size
}