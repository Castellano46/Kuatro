package com.example.reservakuatro.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import android.os.Bundle
import android.widget.Button
import android.transition.Fade
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.example.reservakuatro.R
import com.example.reservakuatro.util.PreferenceHelper
import com.example.reservakuatro.util.PreferenceHelper.get
import com.example.reservakuatro.util.PreferenceHelper.set

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferences = PreferenceHelper.defaultPrefs(this)
        if (preferences["session", false])
            goToMenu()


        val tvGoRegister = findViewById<TextView>(R.id.tv_go_to_register)
        tvGoRegister.setOnClickListener{
            goToRegister()
        }

        val btnGoMenu = findViewById<Button>(R.id.btn_go_to_menu)
        btnGoMenu.setOnClickListener{
            goToMenuWithFadeTransition()
        }
    }

    private fun goToRegister() {
        val i = Intent( this, RegisterActivity:: class.java)
        startActivity(i)
    }

    private fun goToMenu() {
        val i = Intent(this, MenuActivity::class.java)
        createSessionPreference()
        startActivity(i)
        finish()
    }

    private fun createSessionPreference() {
        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["session"] = true
    }

    private fun goToMenuWithFadeTransition() {
        val i = Intent(this, MenuActivity::class.java)
        createSessionPreference()

        val fade = Fade()
        fade.duration = 2000

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            findViewById(R.id.btn_go_to_menu),
            ViewCompat.getTransitionName(findViewById(R.id.btn_go_to_menu)) ?: "transition_menu_button"
        )

        window.enterTransition = fade

        startActivity(i, options.toBundle())
        finish()
    }
}