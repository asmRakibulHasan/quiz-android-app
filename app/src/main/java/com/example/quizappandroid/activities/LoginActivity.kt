package com.example.quizappandroid.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.quizappandroid.R
import com.example.quizappandroid.database.DatabaseHelper
import com.example.quizappandroid.utils.SessionManager
import com.example.quizappandroid.utils.ValidationHelper
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var btnLogin: MaterialButton
    private lateinit var tvRegister: MaterialTextView
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
        dbHelper = DatabaseHelper(this)
        sessionManager = SessionManager(this)

        // Check if already logged in
        if (sessionManager.isLoggedIn) {
            navigateToWelcome()
            return
        }

        btnLogin.setOnClickListener {
            loginUser()
        }

        tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    private fun initViews() {
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)
        tvRegister = findViewById(R.id.tv_register)
    }

    private fun loginUser() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        // Validate inputs
        when {
            !ValidationHelper.isValidEmail(email) -> {
                etEmail.error = "Please enter a valid email"
                etEmail.requestFocus()
                return
            }
            password.isEmpty() -> {
                etPassword.error = "Please enter password"
                etPassword.requestFocus()
                return
            }
        }

        // Check credentials
        if (dbHelper.checkUser(email, password)) {
            val user = dbHelper.getUserByEmail(email)
            user?.let {
                sessionManager.createLoginSession(it.id, it.fullName, it.email)
                showAlertDialog("Success", "Login successful!") {
                    navigateToWelcome()
                }
            }
        } else {
            showAlertDialog("Error", "Invalid email or password")
        }
    }

    private fun navigateToWelcome() {
        startActivity(Intent(this, WelcomeActivity::class.java))
        finish()
    }

    private fun showAlertDialog(title: String, message: String, onDismiss: (() -> Unit)? = null) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                onDismiss?.invoke()
            }
            .show()
    }
}
