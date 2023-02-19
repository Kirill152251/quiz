package com.quiz.data.remote.model

import com.quiz.domain.models.Question

data class QuestionItem(
    val category: String,
    val correctAnswer: String,
    val difficulty: String,
    val id: String,
    val incorrectAnswers: List<String>,
    val question: String,
) {
    fun toDomain(): Question {
        val allAnswers = mutableListOf<String>()
        allAnswers.add(correctAnswer)
        incorrectAnswers.forEach {
            allAnswers.add(it)
        }
        return Question(
            id, correctAnswer, incorrectAnswers, question, allAnswers.shuffled()
        )
    }
}