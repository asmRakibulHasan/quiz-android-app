package com.example.quizappandroid.models

data class Score(
    val id: Int = 0,
    val userId: Int,
    val level: String,
    val totalQuestions: Int,
    val correctAnswers: Int,
    val percentage: Double,
    val date: String = ""
)
