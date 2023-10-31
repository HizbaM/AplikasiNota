package com.example.aplikasinota

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var etNamaPengguna0: TextInputEditText
    private lateinit var etKataSandi0: TextInputEditText
    private lateinit var btnBuatAkun0: MaterialButton
    private lateinit var tvBuatAkun0: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etNamaPengguna0 = findViewById(R.id.etNamaPengguna)
        etKataSandi0 = findViewById(R.id.etKataSandi)
        btnBuatAkun0 = findViewById(R.id.btnBuatAkun)
        tvBuatAkun0 = findViewById(R.id.tvBuatAkun)

        btnBuatAkun0.setOnClickListener {
            loginPengguna()
        }

        tvBuatAkun0.setOnClickListener {
            val mulaiintent = Intent(this, BuatAkunActivity::class.java)
            startActivity(mulaiintent)
        }
    }

    private fun loginPengguna() {
        val email = etNamaPengguna0.text.toString()
        val password = etKataSandi0.text.toString()
        val cekValidasi = validasiData(email, password)

        if (!cekValidasi) {
            return
        }

        loginAkundiFirebase(email, password)
    }

    private fun loginAkundiFirebase(email: String, password: String) {
        val firebaseAuth = FirebaseAuth.getInstance()
        changeInProgress(true)
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                changeInProgress(false)
                if (task.isSuccessful) {
                    if (firebaseAuth.currentUser?.isEmailVerified == true) {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Email not verified, Please verify your email.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, task.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun changeInProgress(inProgress: Boolean) {
        val progressBar = findViewById<View>(R.id.viewBawahTombol)
        val loginBtn = findViewById<Button>(R.id.btnBuatAkun)

        if (inProgress) {
            progressBar.visibility = View.VISIBLE
            loginBtn.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            loginBtn.visibility = View.VISIBLE
        }
    }

    private fun validasiData(email: String, password: String): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etNamaPengguna0.error = "Email is invalid"
            return false
        }
        if (password.length < 6) {
            etKataSandi0.error = "Password length is invalid"
            return false
        }
        return true
    }
}
