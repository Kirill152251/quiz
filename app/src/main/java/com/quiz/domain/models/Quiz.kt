package com.quiz.domain.models

data class Quiz(
    val questions: List<Question>,
    val numberOfQuestions: Int,
    val difficulty: Difficulty,
    val categories: List<Category>
)
