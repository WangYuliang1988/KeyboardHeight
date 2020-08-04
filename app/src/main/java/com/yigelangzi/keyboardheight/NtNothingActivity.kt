package com.yigelangzi.keyboardheight

import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class NtNothingActivity : AppCompatActivity(), KeyboardHeightObserver {

    companion object {
        private const val TAG = "NtNothingActivity"
    }

    private var keyboardHeightProvider: KeyboardHeightProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nt_nothing)

        keyboardHeightProvider = KeyboardHeightProvider(this)
        // make sure to start the keyboard height provider after the onResume
        // of this activity. This is because a popup window must be initialised
        // and attached to the activity root view.
        Handler().post { keyboardHeightProvider!!.start() }
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