package com.quiz.data.remote.model

import com.quiz.domain.models.Category
import com.quiz.domain.models.Category.*
import com.quiz.domain.models.QuestionsMetadata

data class MetaDataResponse(
    val byCategory: ByCategory
) {
    fun toMetadata(): QuestionsMetadata {
        val metadata = mutableMapOf<Category, Int>()
        with(metadata) {
            put(FILM_AND_TV, this@MetaDataResponse.byCategory.films)
            put(GENERAL_KNOWLEDGE, this@MetaDataResponse.byCategory.general)
            put(SOCIETY_AND_CULTURE, this@MetaDataResponse.byCategory.societyAndCulture)
            put(HISTORY, this@MetaDataResponse.byCategory.history)
            put(SPORT_AND_LEISURE, this@MetaDataResponse.byCategory.sportAndLeisure)
            put(GEOGRAPHY, this@MetaDataResponse.byCategory.geography)
            put(MUSIC, this@MetaDataResponse.byCategory.music)
            put(SCIENCE, this@MetaDataResponse.byCategory.science)
            put(FOOD_AND_DRINKS, this@MetaDataResponse.byCategory.foodAndDrink)
            put(ART_AND_LITERATURE, this@MetaDataResponse.byCategory.artAndLit)
        }
        return QuestionsMetadata(metadata)
    }
}