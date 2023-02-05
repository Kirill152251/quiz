package com.quiz.domain.models

data class QuestionsMetadata(
    val byCategory: Map<Category, Int>
)