package com.quiz.domain.models

import com.quiz.domain.models.Category
import com.quiz.domain.models.Difficulty
import com.quiz.domain.models.Question

data class Quiz(
    val id: String,
    val questions: List<Question>,
    val numberOfQuestions: Int,
    val difficulty: Difficulty,
    val categories: Category
)
