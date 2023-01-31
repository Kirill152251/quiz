package com.models

data class QuestionsMetadata(
    val byDifficulty: Map<String, Int>,
    val byCategory: Map<String, Int>
)