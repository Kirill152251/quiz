package com.quiz.domain.models

import com.quiz.domain.models.Category

data class QuestionsMetadata(
    val byCategory: Map<Category, Int>
)