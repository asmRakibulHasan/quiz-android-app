package com.example.quizappandroid.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.quizappandroid.models.Score
import com.example.quizappandroid.models.User

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "QuizApp.db"
        private const val DATABASE_VERSION = 1

        // Table Names
        private const val TABLE_USERS = "users"
        private const val TABLE_SCORES = "scores"

        // Users Table Columns
        private const val KEY_USER_ID = "user_id"
        private const val KEY_NAME = "full_name"
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "password"

        // Scores Table Columns
        private const val KEY_SCORE_ID = "score_id"
        private const val KEY_LEVEL = "difficulty_level"
        private const val KEY_TOTAL_Q = "total_questions"
        private const val KEY_CORRECT = "correct_answers"
        private const val KEY_PERCENTAGE = "score_percentage"
        private const val KEY_DATE = "completed_at"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Create Users Table
        val createUsersTable = """
            CREATE TABLE $TABLE_USERS (
                $KEY_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $KEY_NAME TEXT NOT NULL,
                $KEY_EMAIL TEXT UNIQUE NOT NULL,
                $KEY_PASSWORD TEXT NOT NULL
            )
        """.trimIndent()
        db?.execSQL(createUsersTable)

        // Create Scores Table
        val createScoresTable = """
            CREATE TABLE $TABLE_SCORES (
                $KEY_SCORE_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $KEY_USER_ID INTEGER NOT NULL,
                $KEY_LEVEL TEXT NOT NULL,
                $KEY_TOTAL_Q INTEGER NOT NULL,
                $KEY_CORRECT INTEGER NOT NULL,
                $KEY_PERCENTAGE REAL NOT NULL,
                $KEY_DATE DATETIME DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY ($KEY_USER_ID) REFERENCES $TABLE_USERS($KEY_USER_ID)
            )
        """.trimIndent()
        db?.execSQL(createScoresTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_SCORES")
        onCreate(db)
    }

    // User Registration
    fun registerUser(name: String, email: String, password: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(KEY_NAME, name)
            put(KEY_EMAIL, email)
            put(KEY_PASSWORD, password)
        }

        val result = db.insert(TABLE_USERS, null, values)
        db.close()
        return result != -1L
    }

    // Check if user exists
    fun checkUser(email: String, password: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_USERS WHERE $KEY_EMAIL=? AND $KEY_PASSWORD=?"
        val cursor = db.rawQuery(query, arrayOf(email, password))
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }

    // Check if email exists
    fun checkEmail(email: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_USERS WHERE $KEY_EMAIL=?"
        val cursor = db.rawQuery(query, arrayOf(email))
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }

    // Get user by email
    fun getUserByEmail(email: String): User? {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_USERS WHERE $KEY_EMAIL=?"
        val cursor = db.rawQuery(query, arrayOf(email))

        var user: User? = null
        if (cursor.moveToFirst()) {
            user = User(
                id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_USER_ID)),
                fullName = cursor.getString(cursor.getColumnIndexOrThrow(KEY_NAME)),
                email = cursor.getString(cursor.getColumnIndexOrThrow(KEY_EMAIL)),
                password = cursor.getString(cursor.getColumnIndexOrThrow(KEY_PASSWORD))
            )
        }
        cursor.close()
        db.close()
        return user
    }

    // Save quiz score
    fun saveScore(userId: Int, level: String, totalQ: Int, correct: Int, percentage: Double): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(KEY_USER_ID, userId)
            put(KEY_LEVEL, level)
            put(KEY_TOTAL_Q, totalQ)
            put(KEY_CORRECT, correct)
            put(KEY_PERCENTAGE, percentage)
        }

        val result = db.insert(TABLE_SCORES, null, values)
        db.close()
        return result != -1L
    }

    // Get user's best score for a level
    fun getBestScore(userId: Int, level: String): Double {
        val db = readableDatabase
        val query = """
            SELECT MAX($KEY_PERCENTAGE) as best_score
            FROM $TABLE_SCORES
            WHERE $KEY_USER_ID=? AND $KEY_LEVEL=?
        """.trimIndent()
        val cursor = db.rawQuery(query, arrayOf(userId.toString(), level))

        var bestScore = 0.0
        if (cursor.moveToFirst()) {
            bestScore = cursor.getDouble(cursor.getColumnIndexOrThrow("best_score"))
        }
        cursor.close()
        db.close()
        return bestScore
    }

    // Get recent scores for user
    fun getRecentScores(userId: Int, limit: Int = 5): List<Score> {
        val scores = mutableListOf<Score>()
        val db = readableDatabase
        val query = """
            SELECT * FROM $TABLE_SCORES
            WHERE $KEY_USER_ID=?
            ORDER BY $KEY_DATE DESC
            LIMIT ?
        """.trimIndent()
        val cursor = db.rawQuery(query, arrayOf(userId.toString(), limit.toString()))

        if (cursor.moveToFirst()) {
            do {
                val score = Score(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_SCORE_ID)),
                    userId = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_USER_ID)),
                    level = cursor.getString(cursor.getColumnIndexOrThrow(KEY_LEVEL)),
                    totalQuestions = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_TOTAL_Q)),
                    correctAnswers = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_CORRECT)),
                    percentage = cursor.getDouble(cursor.getColumnIndexOrThrow(KEY_PERCENTAGE)),
                    date = cursor.getString(cursor.getColumnIndexOrThrow(KEY_DATE))
                )
                scores.add(score)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return scores
    }
}
