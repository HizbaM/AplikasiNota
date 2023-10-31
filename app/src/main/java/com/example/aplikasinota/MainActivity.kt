package com.example.aplikasinota

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var btnTambahNote0: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTambahNote0=findViewById(R.id.btnTambahNote)

        btnTambahNote0.setOnClickListener {
            val mulaiintent = Intent(this, DetailNotaActivity::class.java)
            startActivity(mulaiintent)
        }
    }
}