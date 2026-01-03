package com.example.quizappandroid.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.quizappandroid.R
import com.example.quizappandroid.utils.SessionManager

class SplashActivity : AppCompatActivity() {

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        sessionManager = SessionManager(this)

        // Show splash screen for 2 seconds then navigate
        Handler(Looper.getMainLooper()).postDelayed({
            checkLoginStatus()
        }, 2000) // 2 seconds delay
    }

    private fun checkLoginStatus() {
        if (sessionManager.isLoggedIn) {
            // User is logged in, go to Welcome Screen
            startActivity(Intent(this, WelcomeActivity::class.java))
        } else {
            // User is not logged in, go to Login Screen
            startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()
    }
}
