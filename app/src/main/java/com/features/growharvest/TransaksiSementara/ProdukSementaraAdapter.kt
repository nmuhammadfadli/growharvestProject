package com.features.growharvest.TransaksiSementara

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.features.growharvest.R

class ProdukSementaraAdapter(
    private val productList: List<ProdukSementara>,
    private val onItemClick: (ProdukSementara) -> Unit
) : RecyclerView.Adapter<ProdukSementaraAdapter.ViewHolder>() {

    private val cartList = mutableListOf<ProdukSementara>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.productName)
        val priceTextView: TextView = itemView.findViewById(R.id.productPrice)
        val imageProduct: ImageView = itemView.findViewById(R.id.productImage)
        val stokTextView: TextView = itemView.findViewById(R.id.productStock)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = if (cartList.isNotEmpty() && position < cartList.size) {
            cartList[position]
        } else if (position < productList.size) {
            productList[position]
        } else {
            // Jika terjadi situasi yang tidak diharapkan, berikan nilai default atau tindakan lain
            ProdukSementara("", 0, R.drawable.product1, 0)
        }

        holder.nameTextView.text = product.name
        holder.priceTextView.text = "$${product.price}"
        holder.stokTextView.text = "Stok: ${product.stock}"
        holder.imageProduct.setImageResource(product.gambar)

        holder.itemView.setOnClickListener { onItemClick(product) }
    }

    fun setCartList(cartItems: List<ProdukSementara>) {
        cartList.clear()
        cartList.addAll(cartItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = productList.size
}