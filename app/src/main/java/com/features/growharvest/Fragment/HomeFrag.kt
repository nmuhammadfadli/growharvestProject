package com.features.growharvest.Fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.features.growharvest.R
import com.features.growharvest.Transaksi.DaftarProdukActivity
import com.features.growharvest.TransaksiSementara.KeranjangSementaraActivity

class HomeFrag : Fragment(R.layout.fragment_home) {
    private lateinit var btnTransaksi: ImageButton
    private lateinit var btnAccount: ImageButton
    private lateinit var btnProduct: ImageButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAccount = view.findViewById(R.id.btnAccount)
        btnTransaksi = view.findViewById(R.id.btnTransaksi)
        btnProduct = view.findViewById(R.id.btnProduct)

        // Ambil username langsung dari SharedPreferences
        val username = getUsernameFromSharedPreferences()

        // Tampilkan nama pengguna pada UI
        val txtUsername: TextView = view.findViewById(R.id.txt_session)
        txtUsername.text = "Halo, $username!"

        btnAccount.setOnClickListener {
            // Intent ke activity AccountFrag
        }

        btnProduct.setOnClickListener {
            val intent = Intent(requireActivity(), DaftarProdukActivity::class.java)
            startActivity(intent)
        }

        btnTransaksi.setOnClickListener {
            val intent = Intent(requireActivity(), KeranjangSementaraActivity::class.java)
            requireActivity().startActivity(intent)
        }
    }

    private fun getUsernameFromSharedPreferences(): String {
        val sharedPreferences = requireActivity().getSharedPreferences("user_session", Context.MODE_PRIVATE)
        return sharedPreferences.getString("username", "") ?: ""
    }
}
