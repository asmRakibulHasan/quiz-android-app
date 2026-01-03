package com.example.quizappandroid.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "QuizAppPrefs"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
        private const val KEY_EMAIL = "email"
        private const val KEY_NAME = "name"
        private const val KEY_USER_ID = "userId"
    }

    fun createLoginSession(userId: Int, name: String, email: String) {
        prefs.edit().apply {
            putBoolean(KEY_IS_LOGGED_IN, true)
            putInt(KEY_USER_ID, userId)
            putString(KEY_NAME, name)
            putString(KEY_EMAIL, email)
            apply()
        }
    }

    val isLoggedIn: Boolean
        get() = prefs.getBoolean(KEY_IS_LOGGED_IN, false)

    val userId: Int
        get() = prefs.getInt(KEY_USER_ID, -1)

    val userName: String
        get() = prefs.getString(KEY_NAME, "") ?: ""

    val userEmail: String
        get() = prefs.getString(KEY_EMAIL, "") ?: ""

    fun logout() {
        prefs.edit().clear().apply()
    }
}
