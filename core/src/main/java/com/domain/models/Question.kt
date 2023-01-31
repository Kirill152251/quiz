package com.domain.models

data class Question(
    val id: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>,
    val question: String,
)