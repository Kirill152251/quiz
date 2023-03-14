package com.quiz.domain.models

enum class Category {
    ART_AND_LITERATURE,
    FILM_AND_TV,
    FOOD_AND_DRINKS,
    GENERAL_KNOWLEDGE,
    GEOGRAPHY,
    HISTORY,
    MUSIC,
    SCIENCE,
    SOCIETY_AND_CULTURE,
    SPORT_AND_LEISURE;

    fun convertToQueryString(): String {
        return when (this) {
            ART_AND_LITERATURE -> "art_and_literature"
            FILM_AND_TV -> "film_and_tv"
            FOOD_AND_DRINKS -> "food_and_drinks"
            GENERAL_KNOWLEDGE -> "general_knowledge"
            GEOGRAPHY -> "geography"
            HISTORY -> "history"
            MUSIC -> "music"
            SCIENCE -> "science"
            SOCIETY_AND_CULTURE -> "society_and_culture"
            SPORT_AND_LEISURE -> "sport_and_leisure"
        }
    }

    fun convertToUIString(): String {
        return when (this) {
            ART_AND_LITERATURE -> "Art & Literature"
            FILM_AND_TV -> "Films & TV"
            FOOD_AND_DRINKS -> "Food & Drinks"
            GENERAL_KNOWLEDGE -> "General Knowledge"
            GEOGRAPHY -> "Geography"
            HISTORY -> "History"
            MUSIC -> "Music"
            SCIENCE -> "Science"
            SOCIETY_AND_CULTURE -> "Society"
            SPORT_AND_LEISURE -> "Sport"
        }
    }
}