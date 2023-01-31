package com.models

enum class Difficulty {

    HARD, MEDIUM, EASY;

    fun convertToString(): String {
        return when(this) {
            HARD -> "hard"
            MEDIUM -> "medium"
            EASY -> "easy"
        }
    }
}


