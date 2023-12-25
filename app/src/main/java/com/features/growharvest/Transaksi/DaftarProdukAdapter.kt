package com.features.growharvest.Transaksi

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.features.growharvest.R

class DaftarProdukAdapter(
    private val context: Context,
    private val productList: List<Produk>,
    private val onAddToCartClick: (Produk, Int) -> Unit
) : RecyclerView.Adapter<DaftarProdukAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        val productImage: ImageView = itemView.findViewById(R.id.productImage)
        val productStock: TextView = itemView.findViewById(R.id.productStock)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]

        holder.productName.text = product.nama_produk
        holder.productPrice.text = "Rp ${product.harga_produk}"
        holder.productStock.text = "Stok: ${product.stok_produk}"
        Glide.with(context)
            .load(product.gambar_produk)
            .into(holder.productImage)

        holder.itemView.setOnClickListener {
            showQuantityDialog(product)
        }
    }

    private fun showQuantityDialog(product: Produk) {
        val dialogBuilder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)
        val dialogView = inflater.inflate(R.layout.dialog_quantity, null)
        dialogBuilder.setView(dialogView)

        val quantityPicker: NumberPicker = dialogView.findViewById(R.id.quantityPicker)
        quantityPicker.minValue = 1
        quantityPicker.maxValue = 10

        dialogBuilder.setTitle("Pilih Kuantitas")
        dialogBuilder.setPositiveButton("Tambah ke Keranjang") { _, _ ->
            val selectedQuantity = quantityPicker.value
            onAddToCartClick.invoke(product, selectedQuantity)
        }
        dialogBuilder.setNegativeButton("Batal") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
