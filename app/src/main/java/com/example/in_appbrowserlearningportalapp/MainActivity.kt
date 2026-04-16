package com.example.in_appbrowserlearningportalapp

import android.graphics.Bitmap
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var etAddress: EditText
    private lateinit var progressBar: ProgressBar
    private val defaultUrl = "https://www.google.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI Components
        webView = findViewById(R.id.webView)
        etAddress = findViewById(R.id.etAddress)
        progressBar = findViewById(R.id.progressBar)

        val btnBack: Button = findViewById(R.id.btnBack)
        val btnForward: Button = findViewById(R.id.btnForward)
        val btnRefresh: Button = findViewById(R.id.btnRefresh)
        val btnHome: Button = findViewById(R.id.btnHome)
        val btnGo: Button = findViewById(R.id.btnGo)

        // Shortcut Buttons
        findViewById<Button>(R.id.btnGoogle).setOnClickListener { loadUrl("https://www.google.com") }
        findViewById<Button>(R.id.btnYoutube).setOnClickListener { loadUrl("https://www.youtube.com") }
        findViewById<Button>(R.id.btnWikipedia).setOnClickListener { loadUrl("https://www.wikipedia.org") }
        findViewById<Button>(R.id.btnKhanAcademy).setOnClickListener { loadUrl("https://www.khanacademy.org") }
        findViewById<Button>(R.id.btnUniversity).setOnClickListener { loadUrl("https://www.harvard.edu") }

        // WebView Configuration
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressBar.visibility = ProgressBar.VISIBLE
                etAddress.setText(url)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility = ProgressBar.GONE
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                webView.loadUrl("file:///android_asset/offline.html")
            }

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false // Allow WebView to load the URL
            }
        }

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                progressBar.progress = newProgress
            }
        }

        // Button Event Handling
        btnBack.setOnClickListener {
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                Toast.makeText(this, "No more history", Toast.LENGTH_SHORT).show()
            }
        }

        btnForward.setOnClickListener {
            if (webView.canGoForward()) {
                webView.goForward()
            }
        }

        btnRefresh.setOnClickListener {
            webView.reload()
        }

        btnHome.setOnClickListener {
            loadUrl(defaultUrl)
        }

        btnGo.setOnClickListener {
            val url = etAddress.text.toString()
            loadUrl(url)
        }

        etAddress.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                loadUrl(etAddress.text.toString())
                true
            } else {
                false
            }
        }

        // Handle Android Back Button
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack()
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })

        // Load Default URL
        loadUrl(defaultUrl)
    }

    private fun loadUrl(url: String) {
        var formattedUrl = url.trim()
        if (formattedUrl.isNotEmpty()) {
            if (!formattedUrl.startsWith("http://") && !formattedUrl.startsWith("https://")) {
                formattedUrl = "https://$formattedUrl"
            }
            webView.loadUrl(formattedUrl)
        }
    }
}