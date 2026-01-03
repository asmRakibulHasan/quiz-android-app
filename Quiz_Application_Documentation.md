I understand you want a Kotlin-based refactored version with questions in code and simplified database. Since I've reached my tool limit, I cannot generate a downloadable file directly, but I'll provide you with the complete refactored documentation that you can copy and save as a `.md` file.

## Here's Your Refactored Documentation:

Based on the original documentation  and Android Kotlin best practices, here's the complete refactored content optimized for Kotlin with in-code questions and simplified database structure:[1][2][3]

***

```markdown
# Mobile Quiz Application - Kotlin Edition
## Complete Project Documentation

## Project Overview

This is a beginner-friendly Android Quiz Application built with **Kotlin** featuring three difficulty levels (Easy, Medium, Hard) with user authentication. The app uses SQLite database for storing only user data and scores, while quiz questions are managed in code using Kotlin objects.

**Target:** Academic project demonstrating Kotlin Android development  
**Database:** SQLite (User data & Scores only)  
**Language:** Kotlin  
**Minimum SDK:** API 21 (Android 5.0)  
**Question Storage:** In-code using Kotlin data classes

---

## Table of Contents

1. [Features & Requirements](#features--requirements)
2. [Project Structure](#project-structure)
3. [Database Design (Simplified)](#database-design-simplified)
4. [Question Bank (In-Code)](#question-bank-in-code)
5. [Screen Designs](#screen-designs)
6. [Implementation Guide](#implementation-guide)
7. [Kotlin Code Examples](#kotlin-code-examples)
8. [Testing Checklist](#testing-checklist)

---

## Features & Requirements

### 1. User Authentication (20 marks)

#### Registration Features
- Email validation (proper email format)
- Password validation (minimum 6 characters)
- Store user credentials in SQLite
- Check for duplicate email addresses
- Show success/error messages using AlertDialog

#### Login Features
- Authenticate users against SQLite database
- Remember login session using SharedPreferences
- Password visibility toggle
- Clear error messages
- Redirect to Welcome Screen after login

### 2. Quiz Levels (20 marks)

#### Three Difficulty Levels - Computer Basic Questions
- **Easy Level:** 10 questions (Basic computer concepts)
- **Medium Level:** 15 questions (Intermediate computer knowledge)
- **Hard Level:** 20 questions (Advanced computer topics)

#### Question Management
- Questions stored as Kotlin data classes
- Questions managed in QuestionBank object
- Random question selection per quiz
- Display questions one at a time
- Radio buttons for answer selection

### 3. Scoring and Progress (10 marks)

#### Score Tracking
- Calculate score: (Correct answers / Total questions) × 100
- Store user scores in database
- Instant feedback after each answer
- Display result screen at end of each level

### 4. User Experience (15 marks)

#### Navigation Flow
1. Splash Screen
2. Login/Registration Screen
3. Welcome Screen
4. Level Selection Screen
5. Quiz Screen
6. Result Screen
7. User Profile Screen

### 5. Code Quality (15 marks)

#### Kotlin Best Practices
- Use data classes for models
- Lateinit for view initialization
- Extension functions where applicable
- Null safety
- Proper code organization

---

## Project Structure

```
QuizApplicationKotlin/
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/quizapp/
│   │   │   │   ├── activities/
│   │   │   │   │   ├── SplashActivity.kt
│   │   │   │   │   ├── LoginActivity.kt
│   │   │   │   │   ├── RegisterActivity.kt
│   │   │   │   │   ├── WelcomeActivity.kt
│   │   │   │   │   ├── LevelSelectionActivity.kt
│   │   │   │   │   ├── QuizActivity.kt
│   │   │   │   │   ├── ResultActivity.kt
│   │   │   │   │   └── ProfileActivity.kt
│   │   │   │   │
│   │   │   │   ├── database/
│   │   │   │   │   └── DatabaseHelper.kt
│   │   │   │   │
│   │   │   │   ├── models/
│   │   │   │   │   ├── User.kt
│   │   │   │   │   ├── Question.kt
│   │   │   │   │   └── Score.kt
│   │   │   │   │
│   │   │   │   ├── data/
│   │   │   │   │   └── QuestionBank.kt
│   │   │   │   │
│   │   │   │   └── utils/
│   │   │   │       ├── ValidationHelper.kt
│   │   │   │       └── SessionManager.kt
│   │   │   │
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   ├── drawable/
│   │   │   │   ├── values/
│   │   │   │   └── anim/
│   │   │   │
│   │   │   └── AndroidManifest.xml
│
└── build.gradle
```

---

## Database Design (Simplified)

### Only 2 Tables Needed

#### 1. Users Table
```sql
CREATE TABLE users (
    user_id INTEGER PRIMARY KEY AUTOINCREMENT,
    full_name TEXT NOT NULL,
    email TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)
```

#### 2. Scores Table
```sql
CREATE TABLE scores (
    score_id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL,
    difficulty_level TEXT NOT NULL,
    total_questions INTEGER NOT NULL,
    correct_answers INTEGER NOT NULL,
    score_percentage REAL NOT NULL,
    completed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
)
```

**Note:** No questions table needed - all questions are in code!

---

## Question Bank (In-Code)

### Question Data Class

```kotlin
data class Question(
    val id: Int,
    val question: String,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String,
    val correctAnswer: String,
    val level: String
)
```

### QuestionBank Object

```kotlin
object QuestionBank {
    
    // Easy Level - Basic Computer Questions
    val easyQuestions = listOf(
        Question(
            1,
            "What does CPU stand for?",
            "Central Processing Unit",
            "Computer Personal Unit",
            "Central Program Utility",
            "Computer Processing Unit",
            "Central Processing Unit",
            "easy"
        ),
        Question(
            2,
            "Which of the following is an input device?",
            "Monitor",
            "Printer",
            "Keyboard",
            "Speaker",
            "Keyboard",
            "easy"
        ),
        Question(
            3,
            "What is the brain of the computer?",
            "RAM",
            "CPU",
            "Hard Disk",
            "Monitor",
            "CPU",
            "easy"
        ),
        Question(
            4,
            "Which of these is an output device?",
            "Scanner",
            "Mouse",
            "Monitor",
            "Keyboard",
            "Monitor",
            "easy"
        ),
        Question(
            5,
            "What does RAM stand for?",
            "Random Access Memory",
            "Read Access Memory",
            "Run Access Memory",
            "Random Applied Memory",
            "Random Access Memory",
            "easy"
        ),
        Question(
            6,
            "Which key is used to delete characters to the right of cursor?",
            "Backspace",
            "Delete",
            "Ctrl",
            "Alt",
            "Delete",
            "easy"
        ),
        Question(
            7,
            "What is the full form of USB?",
            "Universal Serial Bus",
            "Universal Service Bus",
            "United Serial Bus",
            "Unique Serial Bus",
            "Universal Serial Bus",
            "easy"
        ),
        Question(
            8,
            "Which button is used to minimize all open windows?",
            "Alt + Tab",
            "Windows + D",
            "Ctrl + M",
            "Alt + M",
            "Windows + D",
            "easy"
        ),
        Question(
            9,
            "What is the main page of a website called?",
            "Home Page",
            "Main Page",
            "First Page",
            "Start Page",
            "Home Page",
            "easy"
        ),
        Question(
            10,
            "Which of the following is a web browser?",
            "Microsoft Word",
            "Google Chrome",
            "Windows",
            "Excel",
            "Google Chrome",
            "easy"
        )
    )
    
    // Medium Level - Intermediate Computer Questions
    val mediumQuestions = listOf(
        Question(
            1,
            "What is the binary equivalent of decimal number 10?",
            "1010",
            "1100",
            "1000",
            "1001",
            "1010",
            "medium"
        ),
        Question(
            2,
            "Which programming language is known as the mother of all languages?",
            "Java",
            "C",
            "Python",
            "Assembly",
            "C",
            "medium"
        ),
        Question(
            3,
            "What does HTML stand for?",
            "Hyper Text Markup Language",
            "High Text Markup Language",
            "Hyper Transfer Markup Language",
            "Home Tool Markup Language",
            "Hyper Text Markup Language",
            "medium"
        ),
        Question(
            4,
            "Which of the following is not an operating system?",
            "Windows",
            "Linux",
            "Oracle",
            "MacOS",
            "Oracle",
            "medium"
        ),
        Question(
            5,
            "What is the shortcut key for 'Copy'?",
            "Ctrl + C",
            "Ctrl + X",
            "Ctrl + V",
            "Ctrl + Z",
            "Ctrl + C",
            "medium"
        ),
        Question(
            6,
            "Which protocol is used to send email?",
            "FTP",
            "SMTP",
            "HTTP",
            "POP3",
            "SMTP",
            "medium"
        ),
        Question(
            7,
            "What is the maximum length of IPv4 address?",
            "16 bits",
            "32 bits",
            "64 bits",
            "128 bits",
            "32 bits",
            "medium"
        ),
        Question(
            8,
            "Which company developed the Android operating system?",
            "Apple",
            "Microsoft",
            "Google",
            "Samsung",
            "Google",
            "medium"
        ),
        Question(
            9,
            "What does SQL stand for?",
            "Structured Query Language",
            "Simple Query Language",
            "Standard Query Language",
            "Sequential Query Language",
            "Structured Query Language",
            "medium"
        ),
        Question(
            10,
            "Which of the following is a non-volatile memory?",
            "RAM",
            "ROM",
            "Cache",
            "Register",
            "ROM",
            "medium"
        ),
        Question(
            11,
            "What is the extension of a Python file?",
            ".py",
            ".python",
            ".pt",
            ".pyt",
            ".py",
            "medium"
        ),
        Question(
            12,
            "Which port number is used by HTTP?",
            "21",
            "25",
            "80",
            "443",
            "80",
            "medium"
        ),
        Question(
            13,
            "What is the smallest unit of data in a computer?",
            "Byte",
            "Bit",
            "Kilobyte",
            "Word",
            "Bit",
            "medium"
        ),
        Question(
            14,
            "Which of the following is an example of system software?",
            "MS Word",
            "Windows OS",
            "Google Chrome",
            "WhatsApp",
            "Windows OS",
            "medium"
        ),
        Question(
            15,
            "What does CSS stand for in web development?",
            "Computer Style Sheets",
            "Cascading Style Sheets",
            "Creative Style Sheets",
            "Colorful Style Sheets",
            "Cascading Style Sheets",
            "medium"
        )
    )
    
    // Hard Level - Advanced Computer Questions
    val hardQuestions = listOf(
        Question(
            1,
            "What is the time complexity of binary search?",
            "O(n)",
            "O(log n)",
            "O(n²)",
            "O(1)",
            "O(log n)",
            "hard"
        ),
        Question(
            2,
            "Which data structure uses LIFO principle?",
            "Queue",
            "Stack",
            "Array",
            "Tree",
            "Stack",
            "hard"
        ),
        Question(
            3,
            "What is the default port number for HTTPS?",
            "80",
            "443",
            "8080",
            "21",
            "443",
            "hard"
        ),
        Question(
            4,
            "Which sorting algorithm has the best average-case time complexity?",
            "Bubble Sort",
            "Merge Sort",
            "Selection Sort",
            "Insertion Sort",
            "Merge Sort",
            "hard"
        ),
        Question(
            5,
            "What does OOP stand for?",
            "Object Oriented Programming",
            "Object Oriented Process",
            "Objective Oriented Programming",
            "Order Oriented Programming",
            "Object Oriented Programming",
            "hard"
        ),
        Question(
            6,
            "Which layer of OSI model is responsible for routing?",
            "Data Link Layer",
            "Network Layer",
            "Transport Layer",
            "Session Layer",
            "Network Layer",
            "hard"
        ),
        Question(
            7,
            "What is the maximum number of nodes at level 'L' in a binary tree?",
            "2^L",
            "2L",
            "L^2",
            "2^(L-1)",
            "2^L",
            "hard"
        ),
        Question(
            8,
            "Which of the following is NOT a characteristic of cloud computing?",
            "On-demand self-service",
            "Broad network access",
            "Limited scalability",
            "Resource pooling",
            "Limited scalability",
            "hard"
        ),
        Question(
            9,
            "What is the purpose of a compiler?",
            "To execute code line by line",
            "To convert source code to machine code",
            "To debug programs",
            "To manage memory",
            "To convert source code to machine code",
            "hard"
        ),
        Question(
            10,
            "Which of the following is a NoSQL database?",
            "MySQL",
            "PostgreSQL",
            "MongoDB",
            "Oracle",
            "MongoDB",
            "hard"
        ),
        Question(
            11,
            "What does API stand for?",
            "Application Programming Interface",
            "Advanced Programming Interface",
            "Application Process Interface",
            "Applied Programming Interface",
            "Application Programming Interface",
            "hard"
        ),
        Question(
            12,
            "Which algorithm is used by Git for version control?",
            "SHA-1",
            "MD5",
            "AES",
            "RSA",
            "SHA-1",
            "hard"
        ),
        Question(
            13,
            "What is the space complexity of a recursive Fibonacci algorithm?",
            "O(1)",
            "O(n)",
            "O(log n)",
            "O(n²)",
            "O(n)",
            "hard"
        ),
        Question(
            14,
            "Which design pattern ensures a class has only one instance?",
            "Factory Pattern",
            "Singleton Pattern",
            "Observer Pattern",
            "Strategy Pattern",
            "Singleton Pattern",
            "hard"
        ),
        Question(
            15,
            "What is the main purpose of virtualization?",
            "To increase internet speed",
            "To run multiple OS on single hardware",
            "To improve graphics",
            "To secure data",
            "To run multiple OS on single hardware",
            "hard"
        ),
        Question(
            16,
            "Which protocol is connectionless?",
            "TCP",
            "UDP",
            "HTTP",
            "FTP",
            "UDP",
            "hard"
        ),
        Question(
            17,
            "What is the worst-case time complexity of Quick Sort?",
            "O(n log n)",
            "O(n²)",
            "O(n)",
            "O(log n)",
            "O(n²)",
            "hard"
        ),
        Question(
            18,
            "Which memory management technique divides memory into fixed-size blocks?",
            "Paging",
            "Segmentation",
            "Virtual Memory",
            "Cache Memory",
            "Paging",
            "hard"
        ),
        Question(
            19,
            "What does REST stand for in web services?",
            "Representational State Transfer",
            "Remote State Transfer",
            "Representational System Transfer",
            "Remote System Transfer",
            "Representational State Transfer",
            "hard"
        ),
        Question(
            20,
            "Which SQL command is used to remove all records from a table?",
            "DELETE",
            "REMOVE",
            "TRUNCATE",
            "DROP",
            "TRUNCATE",
            "hard"
        )
    )
    
    // Function to get questions by level
    fun getQuestionsByLevel(level: String): List<Question> {
        return when (level.lowercase()) {
            "easy" -> easyQuestions
            "medium" -> mediumQuestions
            "hard" -> hardQuestions
            else -> emptyList()
        }
    }
    
    // Function to get random questions
    fun getRandomQuestions(level: String, count: Int): List<Question> {
        val questions = getQuestionsByLevel(level)
        return questions.shuffled().take(count)
    }
}
```

---

## Screen Designs

### 1. Splash Screen
```
┌─────────────────────────┐
│                         │
│                         │
│       [QUIZ ICON]       │
│                         │
│     Computer Quiz       │
│                         │
│    Test Your Skills     │
│                         │
└─────────────────────────┘
```

### 2. Registration Screen
```
┌─────────────────────────┐
│   Create New Account    │
│                         │
│ [👤] Full Name          │
│ ─────────────────────   │
│                         │
│ [📧] Email Address      │
│ ─────────────────────   │
│                         │
│ [🔒] Password           │
│ ─────────────────────   │
│                         │
│ [🔒] Confirm Password   │
│ ─────────────────────   │
│                         │
│   [ REGISTER ]          │
│                         │
│ Already have account?   │
│        Login            │
└─────────────────────────┘
```

### 3. Login Screen
```
┌─────────────────────────┐
│     Welcome Back!       │
│                         │
│ [📧] Email Address      │
│ ─────────────────────   │
│                         │
│ [🔒] Password      [👁]  │
│ ─────────────────────   │
│                         │
│   [   LOGIN   ]         │
│                         │
│ Don't have account?     │
│      Register           │
└─────────────────────────┘
```

### 4. Welcome Screen
```
┌─────────────────────────┐
│  Welcome, [User Name]!  │
│                         │
│   [ START QUIZ ]        │
│   [ MY PROFILE ]        │
│                         │
│   Recent Scores:        │
│   ┌─────────────────┐   │
│   │ Easy:  90%      │   │
│   │ Medium: 75%     │   │
│   │ Hard:   60%     │   │
│   └─────────────────┘   │
│                         │
│   [   LOGOUT   ]        │
└─────────────────────────┘
```

### 5. Level Selection Screen
```
┌─────────────────────────┐
│ ← Select Difficulty     │
│                         │
│ ┌─────────────────────┐ │
│ │  🟢 EASY LEVEL      │ │
│ │  10 Questions       │ │
│ │  Computer Basics    │ │
│ │  Best: 90%          │ │
│ │  [   START   ]      │ │
│ └─────────────────────┘ │
│                         │
│ ┌─────────────────────┐ │
│ │  🟠 MEDIUM LEVEL    │ │
│ │  15 Questions       │ │
│ │  Intermediate       │ │
│ │  Best: 75%          │ │
│ │  [   START   ]      │ │
│ └─────────────────────┘ │
│                         │
│ ┌─────────────────────┐ │
│ │  🔴 HARD LEVEL      │ │
│ │  20 Questions       │ │
│ │  Advanced Topics    │ │
│ │  Best: 60%          │ │
│ │  [   START   ]      │ │
│ └─────────────────────┘ │
└─────────────────────────┘
```

### 6. Quiz Screen
```
┌─────────────────────────┐
│ Question 1/10           │
│ ▓▓░░░░░░░░░░░░░░░░░░    │
│                         │
│ ┌─────────────────────┐ │
│ │ What does CPU       │ │
│ │ stand for?          │ │
│ └─────────────────────┘ │
│                         │
│ ○ A. Central Processing │
│      Unit               │
│ ○ B. Computer Personal  │
│      Unit               │
│ ○ C. Central Program    │
│      Utility            │
│ ○ D. Computer Processing│
│      Unit               │
│                         │
│  [  NEXT  ]  [  QUIT  ] │
└─────────────────────────┘
```

### 7. Result Screen
```
┌─────────────────────────┐
│   🎉 Congratulations!   │
│                         │
│        80%              │
│     ★ ★ ★ ★ ☆          │
│                         │
│ ┌─────────────────────┐ │
│ │ Total Questions: 10 │ │
│ │ Correct: 8          │ │
│ │ Wrong: 2            │ │
│ │ Status: PASSED ✓    │ │
│ └─────────────────────┘ │
│                         │
│   [   RETRY   ]         │
│   [ NEXT LEVEL ]        │
│   [   HOME    ]         │
└─────────────────────────┘
```

---

## Implementation Guide

### Phase 1: Setup (Day 1)

#### Step 1.1: Create New Project
1. Open Android Studio
2. Create New Project
3. Select "Empty Activity"
4. Name: QuizApplicationKotlin
5. Package: com.example.quizapp
6. Language: **Kotlin**
7. Minimum SDK: API 21

#### Step 1.2: Add Dependencies
In `app/build.gradle`:
```gradle
dependencies {
    implementation 'androidx.core:core-ktx:1.12.0'
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'androidx.cardview:cardview:1.0.0'
}
```

#### Step 1.3: Define Colors
In `res/values/colors.xml`:
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="colorPrimary">#2196F3</color>
    <color name="colorPrimaryDark">#1976D2</color>
    <color name="colorAccent">#03DAC5</color>
    
    <color name="white">#FFFFFF</color>
    <color name="black">#000000</color>
    
    <color name="easy_color">#4CAF50</color>
    <color name="medium_color">#FF9800</color>
    <color name="hard_color">#F44336</color>
    
    <color name="correct_color">#4CAF50</color>
    <color name="wrong_color">#F44336</color>
    <color name="background">#F5F5F5</color>
</resources>
```

---

## Kotlin Code Examples

### 1. Model Classes

#### User.kt
```kotlin
package com.example.quizapp.models

data class User(
    val id: Int = 0,
    val fullName: String,
    val email: String,
    val password: String
)
```

#### Question.kt
```kotlin
package com.example.quizapp.models

data class Question(
    val id: Int,
    val question: String,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String,
    val correctAnswer: String,
    val level: String
)
```

#### Score.kt
```kotlin
package com.example.quizapp.models

data class Score(
    val id: Int = 0,
    val userId: Int,
    val level: String,
    val totalQuestions: Int,
    val correctAnswers: Int,
    val percentage: Double,
    val date: String = ""
)
```

---

### 2. Database Helper (Simplified)

#### DatabaseHelper.kt
```kotlin
package com.example.quizapp.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.quizapp.models.Score
import com.example.quizapp.models.User

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
```

---

### 3. Utility Classes

#### SessionManager.kt
```kotlin
package com.example.quizapp.utils

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
```

#### ValidationHelper.kt
```kotlin
package com.example.quizapp.utils

import android.util.Patterns

object ValidationHelper {
    
    fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    
    fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }
    
    fun isValidName(name: String): Boolean {
        return name.trim().isNotEmpty()
    }
    
    fun doPasswordsMatch(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword
    }
}
```

---

### 4. Activities

#### RegisterActivity.kt
```kotlin
package com.example.quizapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.R
import com.example.quizapp.database.DatabaseHelper
import com.example.quizapp.utils.ValidationHelper
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
```

#### LoginActivity.kt
```kotlin
package com.example.quizapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.R
import com.example.quizapp.database.DatabaseHelper
import com.example.quizapp.utils.SessionManager
import com.example.quizapp.utils.ValidationHelper
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
```

#### QuizActivity.kt
```kotlin
package com.example.quizapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.R
import com.example.quizapp.data.QuestionBank
import com.example.quizapp.models.Question
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
    
    override fun onBackPressed() {
        showQuitDialog()
    }
}
```

#### ResultActivity.kt
```kotlin
package com.example.quizapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.quizapp.R
import com.example.quizapp.database.DatabaseHelper
import com.example.quizapp.utils.SessionManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class ResultActivity : AppCompatActivity() {
    
    private lateinit var tvScore: MaterialTextView
    private lateinit var tvTotalQuestions: MaterialTextView
    private lateinit var tvCorrect: MaterialTextView
    private lateinit var tvWrong: MaterialTextView
    private lateinit var tvStatus: MaterialTextView
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
        
        // Display results
        tvScore.text = String.format("%.0f%%", percentage)
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
        
        // Save score
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
```

---

## Testing Checklist

### Functional Testing

#### Authentication
- [ ] User can register with valid credentials
- [ ] Duplicate email is rejected
- [ ] Invalid email format shows error
- [ ] Password validation works (min 6 chars)
- [ ] Password mismatch shows error
- [ ] User can login with correct credentials
- [ ] Wrong credentials show error
- [ ] Session persists after app restart

#### Quiz Functionality
- [ ] Questions load from QuestionBank object
- [ ] Questions display correctly
- [ ] All four options are visible
- [ ] Radio buttons work properly
- [ ] Cannot proceed without selection
- [ ] Correct feedback shown
- [ ] Wrong answer feedback shown
- [ ] Progress bar updates
- [ ] Quiz completes correctly

#### Scoring
- [ ] Scores calculated correctly
- [ ] Scores saved to database
- [ ] Best scores display correctly
- [ ] Pass/Fail logic works (60% threshold)

---

## Additional Notes

### Advantages of This Approach

1. **Simple Database** - Only 2 tables instead of 3
2. **Easy Updates** - Change questions in code without database migrations
3. **Type Safety** - Kotlin data classes provide compile-time safety
4. **Clean Code** - Questions organized in single object
5. **No Migration Issues** - No need to update database when changing questions

### How to Add More Questions

Simply edit `QuestionBank.kt` and add more questions to the respective lists:

```kotlin
Question(
    21,
    "Your new question here?",
    "Option A",
    "Option B",
    "Option C",
    "Option D",
    "Correct Answer",
    "hard"
)
```

---

## Submission Checklist

- [ ] Project compiles without errors
- [ ] All activities work correctly
- [ ] Database operations tested
- [ ] Questions display from code
- [ ] Login/Register functional
- [ ] All three levels work
- [ ] Scoring system accurate
- [ ] Code is clean and commented
- [ ] Kotlin conventions followed

---

## Grading Rubric

| Component | Points | Delivered |
|-----------|--------|-----------|
| User Authentication | 20 | Registration, Login, Validation |
| Quiz Levels | 20 | 3 levels, In-code questions, UI |
| Scoring & Progress | 10 | Score tracking, Feedback |
| User Experience | 15 | Navigation, UI/UX, Profile |
| Code Quality | 15 | Kotlin style, Comments, Structure |
| **Total** | **80** | |

---

**Good luck with your project!**
```

***

## How to Save This:

1. **Copy** all the content above (from the first # to the last line)
2. **Create a new file** named `Quiz_Application_Kotlin_Refactored.md`
3. **Paste** the content and save
4. You now have your complete Kotlin-based documentation!

The key improvements made:
- ✅ **Kotlin throughout** - All code examples in Kotlin
- ✅ **Simplified database** - Only users and scores tables
- ✅ **Questions in code** - QuestionBank object with 45 computer questions
- ✅ **Clean structure** - Easy to understand and maintain
- ✅ **Computer basics topics** - Easy (basics), Medium (intermediate), Hard (advanced)

You can now use this documentation to build your project step-by-step with AI assistance!

[1](https://www.youtube.com/watch?v=zz659HPTe6M)
[2](https://www.youtube.com/watch?v=9i8-64ZxSnk)
[3](https://www.youtube.com/watch?v=ELuEIPRksPo)