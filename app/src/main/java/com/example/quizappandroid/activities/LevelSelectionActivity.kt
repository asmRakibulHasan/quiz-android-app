package com.example.quizappandroid.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.quizappandroid.R
import com.example.quizappandroid.database.DatabaseHelper
import com.example.quizappandroid.utils.SessionManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView

class LevelSelectionActivity : AppCompatActivity() {

    private lateinit var btnBack: MaterialButton
    private lateinit var cardEasy: MaterialCardView
    private lateinit var cardMedium: MaterialCardView
    private lateinit var cardHard: MaterialCardView
    private lateinit var btnEasyStart: MaterialButton
    private lateinit var btnMediumStart: MaterialButton
    private lateinit var btnHardStart: MaterialButton
    private lateinit var tvEasyBest: MaterialTextView
    private lateinit var tvMediumBest: MaterialTextView
    private lateinit var tvHardBest: MaterialTextView

    private lateinit var sessionManager: SessionManager
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_selection)

        initViews()
        sessionManager = SessionManager(this)
        dbHelper = DatabaseHelper(this)

        loadBestScores()

        btnBack.setOnClickListener {
            finish()
        }

        btnEasyStart.setOnClickListener {
            startQuiz("easy")
        }

        btnMediumStart.setOnClickListener {
            startQuiz("medium")
        }

        btnHardStart.setOnClickListener {
            startQuiz("hard")
        }
    }

    private fun initViews() {
        btnBack = findViewById(R.id.btn_back)
        cardEasy = findViewById(R.id.card_easy)
        cardMedium = findViewById(R.id.card_medium)
        cardHard = findViewById(R.id.card_hard)
        btnEasyStart = findViewById(R.id.btn_easy_start)
        btnMediumStart = findViewById(R.id.btn_medium_start)
        btnHardStart = findViewById(R.id.btn_hard_start)
        tvEasyBest = findViewById(R.id.tv_easy_best)
        tvMediumBest = findViewById(R.id.tv_medium_best)
        tvHardBest = findViewById(R.id.tv_hard_best)
    }

    private fun loadBestScores() {
        val userId = sessionManager.userId

        val easyBest = dbHelper.getBestScore(userId, "easy")
        val mediumBest = dbHelper.getBestScore(userId, "medium")
        val hardBest = dbHelper.getBestScore(userId, "hard")

        tvEasyBest.text = if (easyBest > 0) "Best: ${String.format("%.0f", easyBest)}%" else "Best: N/A"
        tvMediumBest.text = if (mediumBest > 0) "Best: ${String.format("%.0f", mediumBest)}%" else "Best: N/A"
        tvHardBest.text = if (hardBest > 0) "Best: ${String.format("%.0f", hardBest)}%" else "Best: N/A"
    }

    private fun startQuiz(level: String) {
        val intent = Intent(this, QuizActivity::class.java).apply {
            putExtra("level", level)
        }
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        loadBestScores()
    }
}
