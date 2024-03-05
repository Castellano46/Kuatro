package com.example.reservakuatro.ui

import android.os.Bundle
import android.os.Handler
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.reservakuatro.R

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {

    private lateinit var lottieAnimationView: LottieAnimationView
    private val SPLASH_TIME_OUT: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        lottieAnimationView = findViewById(R.id.lottieAnimationView)
        lottieAnimationView.setAnimation(R.raw.loading2)
        lottieAnimationView.playAnimation()

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)
    }
}