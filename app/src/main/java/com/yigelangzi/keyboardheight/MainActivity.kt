package com.yigelangzi.keyboardheight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_nt_pan).setOnClickListener {
            startActivity(Intent(this, NtPanActivity::class.java))
        }
        findViewById<Button>(R.id.btn_nt_resize).setOnClickListener {
            startActivity(Intent(this, NtResizeActivity::class.java))
        }
        findViewById<Button>(R.id.btn_nt_unspecified).setOnClickListener {
            startActivity(Intent(this, NtUnspecifiedActivity::class.java))
        }
        findViewById<Button>(R.id.btn_nt_nothing).setOnClickListener {
            startActivity(Intent(this, NtNothingActivity::class.java))
        }
        findViewById<Button>(R.id.btn_web_pan).setOnClickListener {
            startActivity(Intent(this, WebPanActivity::class.java))
        }
        findViewById<Button>(R.id.btn_web_resize).setOnClickListener {
            startActivity(Intent(this, WebResizeActivity::class.java))
        }
        findViewById<Button>(R.id.btn_web_unspecified).setOnClickListener {
            startActivity(Intent(this, WebUnspecifiedActivity::class.java))
        }
        findViewById<Button>(R.id.btn_web_nothing).setOnClickListener {
            startActivity(Intent(this, WebNothingActivity::class.java))
        }
    }
}