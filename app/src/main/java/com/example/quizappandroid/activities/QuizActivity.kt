package com.example.quizappandroid.activities

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.quizappandroid.R
import com.example.quizappandroid.data.QuestionBank
import com.example.quizappandroid.models.Question
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.radiobutton.MaterialRadioButton
import com.google.android.material.textview.MaterialTextView
import android.widget.RadioGroup

class QuizActivity : AppCompatActivity() {

    private lateinit var tvQuestionCount: MaterialTextView
    private lateinit var progressBar: LinearProgressIndicator
    private lateinit var tvQuestion: MaterialTextView
    private lateinit var rgOptions: RadioGroup
    private lateinit var rbOptionA: MaterialRadioButton
    private lateinit var rbOptionB: MaterialRadioButton
    private lateinit var rbOptionC: MaterialRadioButton
    private lateinit var rbOptionD: MaterialRadioButton
    private lateinit var btnNext: MaterialButton
    private lateinit var btnQuit: MaterialButton

    private lateinit var questionList: List<Question>
    private var currentQuestionIndex = 0
    private var correctAnswers = 0
    private var wrongAnswers = 0
    private lateinit var difficultyLevel: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        difficultyLevel = intent.getStringExtra("level") ?: "easy"

        initViews()
        loadQuestions()
        displayQuestion()

        btnNext.setOnClickListener {
            checkAnswerAndProceed()
        }

        btnQuit.setOnClickListener {
            showQuitDialog()
        }
    }

    private fun initViews() {
        tvQuestionCount = findViewById(R.id.tv_question_count)
        progressBar = findViewById(R.id.progress_bar)
        tvQuestion = findViewById(R.id.tv_question)
        rgOptions = findViewById(R.id.rg_options)
        rbOptionA = findViewById(R.id.rb_option_a)
        rbOptionB = findViewById(R.id.rb_option_b)
        rbOptionC = findViewById(R.id.rb_option_c)
        rbOptionD = findViewById(R.id.rb_option_d)
        btnNext = findViewById(R.id.btn_next)
        btnQuit = findViewById(R.id.btn_quit)
    }

    private fun loadQuestions() {
        questionList = QuestionBank.getQuestionsByLevel(difficultyLevel)
    }

    private fun displayQuestion() {
        if (currentQuestionIndex < questionList.size) {
            val question = questionList[currentQuestionIndex]

            // Update progress
            val progress = ((currentQuestionIndex + 1) * 100) / questionList.size
            progressBar.progress = progress

            // Update question count
            tvQuestionCount.text = "Question ${currentQuestionIndex + 1}/${questionList.size}"

            // Display question and options
            tvQuestion.text = question.question
            rbOptionA.text = question.optionA
            rbOptionB.text = question.optionB
            rbOptionC.text = question.optionC
            rbOptionD.text = question.optionD

            // Clear previous selection
            rgOptions.clearCheck()

            // Update button text
            btnNext.text = if (currentQuestionIndex == questionList.size - 1) "Finish" else "Next"
        }
    }

    private fun checkAnswerAndProceed() {
        // Check if option selected
        if (rgOptions.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
            return
        }

        // Get selected answer
        val selectedRB = findViewById<RadioButton>(rgOptions.checkedRadioButtonId)
        val selectedAnswer = selectedRB.text.toString()

        // Get correct answer
        val currentQuestion = questionList[currentQuestionIndex]
        val correctAnswer = currentQuestion.correctAnswer

        // Check answer
        if (selectedAnswer == correctAnswer) {
            correctAnswers++
            Toast.makeText(this, "Correct! ✓", Toast.LENGTH_SHORT).show()
        } else {
            wrongAnswers++
            Toast.makeText(this, "Wrong! ✗\nCorrect: $correctAnswer", Toast.LENGTH_SHORT).show()
        }

        // Move to next question or finish
        currentQuestionIndex++
        if (currentQuestionIndex < questionList.size) {
            displayQuestion()
        } else {
            finishQuiz()
        }
    }

    private fun finishQuiz() {
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("level", difficultyLevel)
            putExtra("totalQuestions", questionList.size)
            putExtra("correctAnswers", correctAnswers)
            putExtra("wrongAnswers", wrongAnswers)
        }
        startActivity(intent)
        finish()
    }

    private fun showQuitDialog() {
        AlertDialog.Builder(this)
            .setTitle("Quit Quiz")
            .setMessage("Are you sure you want to quit? Your progress will be lost.")
            .setPositiveButton("Yes") { _, _ -> finish() }
            .setNegativeButton("No", null)
            .show()
    }
}
