package com.models

data class Quiz(
    val id: String,
    val questions: List<Question>,
    val numberOfQuestions: Int,
    val difficulty: Difficulty,
    val categories: Category
)
