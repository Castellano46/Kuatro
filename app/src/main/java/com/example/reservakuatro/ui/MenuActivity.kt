package com.example.reservakuatro.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.reservakuatro.R
import com.example.reservakuatro.util.PreferenceHelper
import com.example.reservakuatro.util.PreferenceHelper.set

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnLogout = findViewById<Button>(R.id.btn_logout)
        btnLogout.setOnClickListener{
            clearSessionPreference()
            goToLogin()
        }
        val btnReservarPista = findViewById<Button>(R.id.btn_reservar_pista)
        btnReservarPista.setOnClickListener {
            goToCreateAppointment()
        }
    }

    private fun goToCreateAppointment() {
        val i = Intent(this, CreateAppointmentActivity::class.java)
        startActivity(i)
    }

    private fun goToLogin() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun clearSessionPreference() {
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["session"] = false
    }
}