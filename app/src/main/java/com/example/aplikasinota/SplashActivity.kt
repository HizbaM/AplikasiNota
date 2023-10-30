package com.example.aplikasinota

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val mulaiintent= Intent(this,BuatAkunActivity::class.java)
            startActivity(mulaiintent)
        }, 1220)
    }
}