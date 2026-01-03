package com.example.quizappandroid.data

import com.example.quizappandroid.models.Question

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
