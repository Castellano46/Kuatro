package com.example.reservakuatro.ui

import android.annotation.SuppressLint
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
import android.view.MotionEvent
import android.view.View
import android.view.animation.ScaleAnimation

class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
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

        btnGoMenu.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startButtonScaleAnimation(btnGoMenu, 1.2f)
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    startButtonScaleAnimation(btnGoMenu, 1.0f)
                }
            }
            false
        }
    }

    private fun startButtonScaleAnimation(view: View, scale: Float) {
        val scaleAnimation = ScaleAnimation(
            1f, scale,
            1f, scale,
            view.pivotX,
            view.pivotY
        )
        scaleAnimation.duration = 200
        scaleAnimation.fillAfter = true
        view.startAnimation(scaleAnimation)
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