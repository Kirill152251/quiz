package com.quiz.domain.models

enum class Difficulty {

    HARD, MEDIUM, EASY;

    fun convertToQueryString(): String {
        return when (this) {
            HARD -> "hard"
            MEDIUM -> "medium"
            EASY -> "easy"
        }
    }

    fun convertToUIString(): String {
        return when (this) {
            HARD -> "HARD"
            MEDIUM -> "MEDIUM"
            EASY -> "EASY"
        }
    }
}


