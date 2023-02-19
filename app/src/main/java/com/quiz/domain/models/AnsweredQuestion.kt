package com.quiz.domain.models

data class AnsweredQuestion(
    val chosenAnswer: String,
    val questionText: String,
    val correctAnswer: String,
    val allAnswers: List<String>
) {
    fun isAnsweredCorrect() = chosenAnswer == correctAnswer
}
