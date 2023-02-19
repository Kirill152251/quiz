package com.quiz.domain.models

data class Question(
    val id: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>,
    val question: String,
    val shuffledAnswers: List<String>,
) {
    companion object {
        val emptyQuestion = Question("0", "", listOf(""), "", listOf(""))
    }
}