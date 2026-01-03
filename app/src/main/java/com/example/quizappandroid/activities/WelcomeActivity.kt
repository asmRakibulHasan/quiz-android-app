package com.example.quizappandroid.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.quizappandroid.R
import com.example.quizappandroid.database.DatabaseHelper
import com.example.quizappandroid.utils.SessionManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class WelcomeActivity : AppCompatActivity() {

    private lateinit var tvWelcome: MaterialTextView
    private lateinit var tvUserEmail: MaterialTextView
    private lateinit var btnStartQuiz: MaterialButton
    private lateinit var btnMyProfile: MaterialButton
    private lateinit var btnLogout: MaterialButton
    private lateinit var tvEasyScore: MaterialTextView
    private lateinit var tvMediumScore: MaterialTextView
    private lateinit var tvHardScore: MaterialTextView

    private lateinit var sessionManager: SessionManager
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        initViews()
        sessionManager = SessionManager(this)
        dbHelper = DatabaseHelper(this)

        displayUserInfo()
        loadBestScores()

        btnStartQuiz.setOnClickListener {
            startActivity(Intent(this, LevelSelectionActivity::class.java))
        }

        btnMyProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        btnLogout.setOnClickListener {
            showLogoutDialog()
        }
    }

    private fun initViews() {
        tvWelcome = findViewById(R.id.tv_welcome)
        tvUserEmail = findViewById(R.id.tv_user_email)
        btnStartQuiz = findViewById(R.id.btn_start_quiz)
        btnMyProfile = findViewById(R.id.btn_my_profile)
        btnLogout = findViewById(R.id.btn_logout)
        tvEasyScore = findViewById(R.id.tv_easy_score)
        tvMediumScore = findViewById(R.id.tv_medium_score)
        tvHardScore = findViewById(R.id.tv_hard_score)
    }

    private fun displayUserInfo() {
        val userName = sessionManager.userName
        val userEmail = sessionManager.userEmail

        tvWelcome.text = "Welcome, $userName!"
        tvUserEmail.text = userEmail
    }

    private fun loadBestScores() {
        val userId = sessionManager.userId

        val easyBest = dbHelper.getBestScore(userId, "easy")
        val mediumBest = dbHelper.getBestScore(userId, "medium")
        val hardBest = dbHelper.getBestScore(userId, "hard")

        tvEasyScore.text = if (easyBest > 0) "${String.format("%.0f", easyBest)}%" else "N/A"
        tvMediumScore.text = if (mediumBest > 0) "${String.format("%.0f", mediumBest)}%" else "N/A"
        tvHardScore.text = if (hardBest > 0) "${String.format("%.0f", hardBest)}%" else "N/A"
    }

    private fun showLogoutDialog() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Yes") { _, _ ->
                sessionManager.logout()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            .setNegativeButton("No", null)
            .show()
    }

    override fun onResume() {
        super.onResume()
        loadBestScores()
    }
}
