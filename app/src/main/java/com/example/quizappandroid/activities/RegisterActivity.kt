package com.example.quizappandroid.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.quizappandroid.R
import com.example.quizappandroid.database.DatabaseHelper
import com.example.quizappandroid.utils.ValidationHelper
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView

class RegisterActivity : AppCompatActivity() {

    private lateinit var etName: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    private lateinit var etConfirmPassword: TextInputEditText
    private lateinit var btnRegister: MaterialButton
    private lateinit var tvLogin: MaterialTextView
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initViews()
        dbHelper = DatabaseHelper(this)

        btnRegister.setOnClickListener {
            registerUser()
        }

        tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun initViews() {
        etName = findViewById(R.id.et_name)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        etConfirmPassword = findViewById(R.id.et_confirm_password)
        btnRegister = findViewById(R.id.btn_register)
        tvLogin = findViewById(R.id.tv_login)
    }

    private fun registerUser() {
        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirmPassword.text.toString().trim()

        // Validate inputs
        when {
            !ValidationHelper.isValidName(name) -> {
                etName.error = "Please enter your name"
                etName.requestFocus()
                return
            }
            !ValidationHelper.isValidEmail(email) -> {
                etEmail.error = "Please enter a valid email"
                etEmail.requestFocus()
                return
            }
            !ValidationHelper.isValidPassword(password) -> {
                etPassword.error = "Password must be at least 6 characters"
                etPassword.requestFocus()
                return
            }
            !ValidationHelper.doPasswordsMatch(password, confirmPassword) -> {
                etConfirmPassword.error = "Passwords do not match"
                etConfirmPassword.requestFocus()
                return
            }
        }

        // Check if email already exists
        if (dbHelper.checkEmail(email)) {
            showAlertDialog("Error", "Email already registered. Please login.")
            return
        }

        // Register user
        val isRegistered = dbHelper.registerUser(name, email, password)

        if (isRegistered) {
            showAlertDialog("Success", "Registration successful! Please login.") {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        } else {
            showAlertDialog("Error", "Registration failed. Please try again.")
        }
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
