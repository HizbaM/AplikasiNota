package com.example.aplikasinota

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class BuatAkunActivity : AppCompatActivity() {
    private lateinit var etNamaPengguna0: TextInputEditText
    private lateinit var etKataSandi0: TextInputEditText
    private lateinit var etKataSandiKonfirmasi0: TextInputEditText
    private lateinit var btnBuatAkun0: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buat_akun)

        etNamaPengguna0 = findViewById(R.id.etNamaPengguna)
        etKataSandi0 = findViewById(R.id.etKataSandi)
        etKataSandiKonfirmasi0 = findViewById(R.id.etKataSandiKonfirmasi)
        btnBuatAkun0 = findViewById(R.id.btnBuatAkun)

        btnBuatAkun0.setOnClickListener {
            BuatAkun()
        }
    }

    fun BuatAkun() {
        val email = etNamaPengguna0.text.toString()
        val password = etKataSandi0.text.toString()
        val konfirmasiPassword = etKataSandiKonfirmasi0.text.toString()
        val cekValidasi = validasiData(email, password, konfirmasiPassword)

        if (!cekValidasi) {
            return
        }

        buatAkundiFirebase(email, password)
    }

    fun buatAkundiFirebase(email: String, password: String) {
        changeInProgress(true)

        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                changeInProgress(false)
                if (task.isSuccessful) {
                    Toast.makeText(this, "Successfully create account, check email to verify", Toast.LENGTH_LONG).show()
                    firebaseAuth.currentUser?.sendEmailVerification()
                    firebaseAuth.signOut()
                    finish()
                } else {
                    Toast.makeText(this, task.exception?.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
    }

    fun changeInProgress(inProgress: Boolean) {
        val barProses = findViewById<View>(R.id.viewBawahTombol)
        val btnBuatAkun1 = findViewById<View>(R.id.btnBuatAkun)

        if (inProgress) {
            barProses.visibility = View.VISIBLE
            btnBuatAkun1.visibility = View.GONE
        } else {
            barProses.visibility = View.GONE
            btnBuatAkun1.visibility = View.VISIBLE
        }
    }

    fun validasiData(email: String, password: String, konfirmasiPassword: String): Boolean {
        val emailPattern = Patterns.EMAIL_ADDRESS

        if (!emailPattern.matcher(email).matches()) {
            etNamaPengguna0.error = "Email is invalid"
            return false
        }

        if (password.length < 6) {
            etKataSandi0.error = "Password length is invalid"
            return false
        }

        if (password != konfirmasiPassword) {
            etKataSandiKonfirmasi0.error = "Passwords do not match"
            return false
        }

        return true
    }
}
