package com.quiz.domain

import com.quiz.domain.models.SavedQuiz
import kotlinx.coroutines.flow.Flow

interface CacheRepository {

    suspend fun deleteQuiz(quiz: SavedQuiz)

    fun getSavedQuizFlow(): Flow<List<SavedQuiz>>

    suspend fun getSavedQuizList(): List<SavedQuiz>

    suspend fun saveQuiz(saveTime: String): Long
}