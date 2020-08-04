package com.yigelangzi.keyboardheight

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class WebResizeActivity : AppCompatActivity(), KeyboardHeightObserver {

    companion object {
        private const val TAG = "WebResizeActivity"
    }

    private var keyboardHeightProvider: KeyboardHeightProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_resize)

        keyboardHeightProvider = KeyboardHeightProvider(this)
        // make sure to start the keyboard height provider after the onResume
        // of this activity. This is because a popup window must be initialised
        // and attached to the activity root view.
        Handler().post { keyboardHeightProvider!!.start() }

        val webView = findViewById<WebView>(R.id.wv_main)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }
        }
        webView.loadUrl("file:///android_asset/web_resize.html")
    }

    override fun onPause() {
        super.onPause()
        keyboardHeightProvider!!.setKeyboardHeightObserver(null)
    }

    override fun onResume() {
        super.onResume()
        keyboardHeightProvider!!.setKeyboardHeightObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        keyboardHeightProvider!!.close()
    }

    override fun onKeyboardHeightChanged(height: Int, orientation: Int) {
        val orientationLabel = if (orientation == Configuration.ORIENTATION_PORTRAIT) "portrait" else "landscape"
        val heightDp = DensityUtil.px2dp(this, height)
        Log.d(TAG, "onKeyboardHeightChanged: ${height}px ${heightDp}dp $orientationLabel")
    }
}