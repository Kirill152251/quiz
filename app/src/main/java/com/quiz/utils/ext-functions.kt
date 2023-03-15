package com.quiz.utils

import com.quiz.domain.models.Category
import com.quiz.domain.models.Difficulty

fun String.toEnumCategory(): Category {
    return when (this) {
        "Art & Literature" -> Category.ART_AND_LITERATURE
        "Films & TV" -> Category.FILM_AND_TV
        "Food & Drinks" -> Category.FOOD_AND_DRINKS
        "General Knowledge" -> Category.GENERAL_KNOWLEDGE
        "Geography" -> Category.GEOGRAPHY
        "History" -> Category.HISTORY
        "Music" -> Category.MUSIC
        "Science" -> Category.SCIENCE
        "Society" -> Category.SOCIETY_AND_CULTURE
        else -> Category.SPORT_AND_LEISURE
    }
}

fun String.toEnumDifficulty(): Difficulty {
    return when (this) {
        "HARD" -> Difficulty.HARD
        "MEDIUM" -> Difficulty.MEDIUM
        else -> Difficulty.EASY
    }
}