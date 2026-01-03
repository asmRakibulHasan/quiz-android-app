package com.example.quizappandroid.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizappandroid.R
import com.example.quizappandroid.adapters.HistoryAdapter
import com.example.quizappandroid.database.DatabaseHelper
import com.example.quizappandroid.models.Score
import com.example.quizappandroid.utils.SessionManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView

class ProfileActivity : AppCompatActivity() {

    private lateinit var tvUserName: MaterialTextView
    private lateinit var tvUserEmail: MaterialTextView
    private lateinit var tvTotalQuizzes: MaterialTextView
    private lateinit var tvAverageScore: MaterialTextView
    private lateinit var tvBestScore: MaterialTextView
    private lateinit var btnBack: MaterialButton
    private lateinit var rvHistory: RecyclerView
    private lateinit var tvNoHistory: MaterialTextView

    private lateinit var sessionManager: SessionManager
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var historyAdapter: HistoryAdapter
    private val historyList = mutableListOf<Score>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initViews()
        sessionManager = SessionManager(this)
        dbHelper = DatabaseHelper(this)

        setupRecyclerView()
        loadUserProfile()
        loadQuizStatistics()
        loadQuizHistory()

        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun initViews() {
        tvUserName = findViewById(R.id.tv_user_name)
        tvUserEmail = findViewById(R.id.tv_user_email)
        tvTotalQuizzes = findViewById(R.id.tv_total_quizzes)
        tvAverageScore = findViewById(R.id.tv_average_score)
        tvBestScore = findViewById(R.id.tv_best_score)
        btnBack = findViewById(R.id.btn_back)
        rvHistory = findViewById(R.id.rv_history)
        tvNoHistory = findViewById(R.id.tv_no_history)
    }

    private fun setupRecyclerView() {
        historyAdapter = HistoryAdapter(historyList) { score ->
            showScoreDetails(score)
        }
        rvHistory.adapter = historyAdapter
        rvHistory.layoutManager = LinearLayoutManager(this)
    }

    private fun loadUserProfile() {
        val userName = sessionManager.userName
        val userEmail = sessionManager.userEmail

        tvUserName.text = userName
        tvUserEmail.text = userEmail
    }

    private fun loadQuizStatistics() {
        val userId = sessionManager.userId
        val recentScores = dbHelper.getRecentScores(userId, limit = 100)

        if (recentScores.isNotEmpty()) {
            // Total quizzes taken
            tvTotalQuizzes.text = "${recentScores.size}"

            // Average score
            val average = recentScores.map { it.percentage }.average()
            tvAverageScore.text = "${String.format("%.1f", average)}%"

            // Best score
            val best = recentScores.maxByOrNull { it.percentage }?.percentage ?: 0.0
            tvBestScore.text = "${String.format("%.0f", best)}%"
        } else {
            tvTotalQuizzes.text = "0"
            tvAverageScore.text = "N/A"
            tvBestScore.text = "N/A"
        }
    }

    private fun loadQuizHistory() {
        val userId = sessionManager.userId
        val recentScores = dbHelper.getRecentScores(userId, limit = 10)

        historyList.clear()
        if (recentScores.isNotEmpty()) {
            historyList.addAll(recentScores)
            historyAdapter.notifyDataSetChanged()
            rvHistory.visibility = View.VISIBLE
            tvNoHistory.visibility = View.GONE
        } else {
            rvHistory.visibility = View.GONE
            tvNoHistory.visibility = View.VISIBLE
        }
    }

    private fun showScoreDetails(score: Score) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_score_details, null)

        val tvLevel: TextView = dialogView.findViewById(R.id.tv_level)
        val tvDate: TextView = dialogView.findViewById(R.id.tv_date)
        val tvTotal: TextView = dialogView.findViewById(R.id.tv_total)
        val tvCorrect: TextView = dialogView.findViewById(R.id.tv_correct)
        val tvPercentage: TextView = dialogView.findViewById(R.id.tv_percentage)
        val btnClose: MaterialButton = dialogView.findViewById(R.id.btn_close)

        // Set level color and text
        val levelText = when (score.level) {
            "easy" -> "Easy Level"
            "medium" -> "Medium Level"
            "hard" -> "Hard Level"
            else -> score.level
        }
        tvLevel.text = levelText

        // Set details
        tvDate.text = "Completed: ${score.date}"
        tvTotal.text = "Total Questions: ${score.totalQuestions}"
        tvCorrect.text = "Correct Answers: ${score.correctAnswers}"
        tvPercentage.text = "Score: ${String.format("%.1f", score.percentage)}%"

        // Build dialog
        AlertDialog.Builder(this)
            .setView(dialogView)
            .create()
            .show()

        btnClose.setOnClickListener {
            (dialogView.parent as? androidx.appcompat.app.AlertDialog)?.dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        loadQuizStatistics()
        loadQuizHistory()
    }
}
