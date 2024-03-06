package com.example.reservakuatro.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.ScaleAnimation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.reservakuatro.R

class RegisterActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val tvGoLogin = findViewById<TextView>(R.id.tv_go_to_login)
        tvGoLogin.setOnClickListener {
            goToLogin()
        }

        tvGoLogin.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startTextScaleAnimation(tvGoLogin, 1.2f)
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    startTextScaleAnimation(tvGoLogin, 1.0f)
                }
            }
            false
        }
    }

    private fun startTextScaleAnimation(view: View, scale: Float) {
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

    private fun goToLogin() {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}
