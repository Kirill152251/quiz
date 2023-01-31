package com

import com.models.Quiz

interface CacheDataSource {
    suspend fun saveQuiz(quiz: Quiz)
    suspend fun deleteQuiz(quiz: Quiz)
    suspend fun getAll(): List<Quiz>
    suspend fun clear()
}