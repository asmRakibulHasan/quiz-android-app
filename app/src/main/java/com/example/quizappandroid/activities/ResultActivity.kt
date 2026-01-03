package com.example.quizappandroid.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.quizappandroid.R
import com.example.quizappandroid.database.DatabaseHelper
import com.example.quizappandroid.utils.SessionManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class ResultActivity : AppCompatActivity() {

    private lateinit var tvScore: MaterialTextView
    private lateinit var tvTotalQuestions: MaterialTextView
    private lateinit var tvCorrect: MaterialTextView
    private lateinit var tvWrong: MaterialTextView
    private lateinit var tvStatus: MaterialTextView
    private lateinit var tvStars: MaterialTextView
    private lateinit var btnRetry: MaterialButton
    private lateinit var btnNextLevel: MaterialButton
    private lateinit var btnHome: MaterialButton

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        initViews()
        dbHelper = DatabaseHelper(this)
        sessionManager = SessionManager(this)

        displayResults()
    }

    private fun initViews() {
        tvScore = findViewById(R.id.tv_score)
        tvTotalQuestions = findViewById(R.id.tv_total_questions)
        tvCorrect = findViewById(R.id.tv_correct)
        tvWrong = findViewById(R.id.tv_wrong)
        tvStatus = findViewById(R.id.tv_status)
        tvStars = findViewById(R.id.tv_stars)
        btnRetry = findViewById(R.id.btn_retry)
        btnNextLevel = findViewById(R.id.btn_next_level)
        btnHome = findViewById(R.id.btn_home)
    }

    private fun displayResults() {
        val level = intent.getStringExtra("level") ?: "easy"
        val totalQuestions = intent.getIntExtra("totalQuestions", 0)
        val correctAnswers = intent.getIntExtra("correctAnswers", 0)
        val wrongAnswers = intent.getIntExtra("wrongAnswers", 0)

        // Calculate percentage
        val percentage = (correctAnswers * 100.0) / totalQuestions

        // Display percentage
        tvScore.text = String.format("%.0f%%", percentage)

        // Display stars based on percentage
        val stars = when {
            percentage >= 90 -> "⭐⭐⭐⭐⭐"
            percentage >= 75 -> "⭐⭐⭐⭐"
            percentage >= 60 -> "⭐⭐⭐"
            percentage >= 40 -> "⭐⭐"
            else -> "⭐"
        }
        tvStars.text = stars

        // Display stats
        tvTotalQuestions.text = "Total Questions: $totalQuestions"
        tvCorrect.text = "Correct: $correctAnswers"
        tvWrong.text = "Wrong: $wrongAnswers"

        // Check pass/fail
        if (percentage >= 60) {
            tvStatus.text = "PASSED ✓"
            tvStatus.setTextColor(ContextCompat.getColor(this, R.color.correct_color))
            btnNextLevel.visibility = View.VISIBLE
        } else {
            tvStatus.text = "FAILED ✗"
            tvStatus.setTextColor(ContextCompat.getColor(this, R.color.wrong_color))
            btnNextLevel.visibility = View.GONE
        }

        // Save score to database
        val userId = sessionManager.userId
        dbHelper.saveScore(userId, level, totalQuestions, correctAnswers, percentage)

        // Button clicks
        btnRetry.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java).apply {
                putExtra("level", level)
            }
            startActivity(intent)
            finish()
        }

        btnNextLevel.setOnClickListener {
            startActivity(Intent(this, LevelSelectionActivity::class.java))
            finish()
        }

        btnHome.setOnClickListener {
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }
    }
}
