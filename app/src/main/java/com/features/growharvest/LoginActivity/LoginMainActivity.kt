package com.features.growharvest.LoginActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.features.growharvest.NavigationBar.NavbarMainActivity
import com.features.growharvest.R
import org.json.JSONException
import com.toxicbakery.bcrypt.Bcrypt

class LoginMainActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: ImageButton

    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inisialisasi elemen UI
        usernameEditText = findViewById(R.id.txt_login_username)
        passwordEditText = findViewById(R.id.txt_login_password)
        loginButton = findViewById(R.id.btnLogin)

        // Inisialisasi antrian permintaan Volley
        requestQueue = Volley.newRequestQueue(this)

        // Tentukan pemantau klik untuk tombol login
        loginButton.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        // Dapatkan username dan password yang dimasukkan
        val username = usernameEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        // Bangun URL API dengan username
        val url = "https://growharvest.my.id/API/login.php?nama_pengguna=$username"

        // Buat permintaan JSON untuk mengambil data pengguna dari API
        val request = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                try {
                    // Tangani respons dari API
                    if (response.length() > 0) {
                        // Ekstrak data pengguna dari respons
                        val userData = response.getJSONObject(0)

                        // Dapatkan kata sandi terenkripsi dari respons API
                        val hashedPasswordFromApi = userData.getString("kata_sandi")

                        // Periksa apakah kata sandi yang dimasukkan cocok dengan kata sandi terenkripsi
                        if (Bcrypt.verify(password, hashedPasswordFromApi.toByteArray())) {
                            // Kata sandi cocok, login berhasil
                            saveUsernameToSharedPreferences(username)
                            Log.d("LoginActivity", "Login berhasil")
                            showToast("Login Berhasil!")
                            val intent = Intent(this, NavbarMainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // Kata sandi tidak cocok
                            Log.d("LoginActivity", "Kata sandi salah")
                            showToast("Kata Sandi Salah")

                        }
                    } else {
                        // Pengguna tidak ditemukan
                        Log.d("LoginActivity", "Pengguna tidak ditemukan")
                        showToast("Pengguna tidak ditemukan")
                    }
                } catch (e: JSONException) {
                    // Tangani kesalahan parsing JSON
                    Log.e("LoginActivity", "Error parsing JSON: ${e.message}")
                    showToast("Error pada Json")
                }
            },
            Response.ErrorListener { error ->
                // Tangani kesalahan
                Log.e("LoginActivity", "Error: ${error.message}")
            })

        // Tambahkan permintaan ke antrian permintaan Volley
        requestQueue.add(request)
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    private fun saveUsernameToSharedPreferences(username: String) {
        val sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.apply()
    }

    private fun checkPassword(inputPassword: String, hashedPassword: String): Boolean {
        // Gunakan password_verify atau metode yang sesuai untuk memeriksa kata sandi terenkripsi
        // Dalam kasus ini, kita melakukan verifikasi di sisi server, jadi kita tidak dapat menggunakan password_verify dari PHP.
        // Sebaliknya, kita melakukan perbandingan string langsung.

        return inputPassword == hashedPassword
    }
}
