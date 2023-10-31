package com.example.aplikasinota

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailNotaActivity : AppCompatActivity() {

    private lateinit var ibSimpanNota0: ImageButton
    private lateinit var etJudulNota0: EditText
    private lateinit var etIsiNota0: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_nota)

        ibSimpanNota0=findViewById(R.id.ibCeklis)
        etJudulNota0=findViewById(R.id.etJudulNota)
        etIsiNota0=findViewById(R.id.etisiNota)

        ibSimpanNota0.setOnClickListener {
            simpanNota()
        }


    }

    fun simpanNota() {
        val noteTitle = etJudulNota0.text.toString()
        val noteContent = etIsiNota0.text.toString()
        if (noteTitle.isNullOrEmpty()) {
            etJudulNota0.error = "Judul Belum Dimasukkan"
            return
        }

        // Lanjutkan dengan operasi penyimpanan catatan Anda di sini
    }
}