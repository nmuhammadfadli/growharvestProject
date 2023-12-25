package com.features.growharvest.LoginActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient
import com.features.growharvest.R

class WebViewMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view)

        val webView: WebView = findViewById(R.id.webView)

        // Enable JavaScript
        webView.settings.javaScriptEnabled = true

        // Load a web page or execute JavaScript code

        // Contoh load web page:
        webView.loadUrl("https://growharvest.my.id/API/login.php")

        // Atau contoh eksekusi kode JavaScript:
        // webView.loadData("<html><body><h1>Hello, JavaScript!</h1></body></html>", "text/html", "UTF-8")

        // Set WebViewClient untuk menangani navigasi di dalam WebView
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl("https://growharvest.my.id/API/login.php")
                return true
            }
        }
    }
}