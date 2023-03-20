package com.quiz.domain

import com.quiz.domain.models.SavedQuiz
import kotlinx.coroutines.flow.Flow

interface CacheDataSource {

    suspend fun saveQuiz(quiz: SavedQuiz): Long
    fun getQuizFlow(): Flow<List<SavedQuiz>>
    suspend fun getQuizList(): List<SavedQuiz>
    suspend fun deleteQuiz(quiz: SavedQuiz)
}