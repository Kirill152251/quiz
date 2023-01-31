package com.repository

import com.models.Category
import com.models.Difficulty
import com.models.QuestionsMetadata
import com.models.Quiz

interface QuizRepository {

    suspend fun getAllLikedQuizzes(): List<Quiz>
    suspend fun saveLikedQuiz(quiz: Quiz)
    suspend fun clearLikesQuizzes()
    suspend fun deleteLikedQuizzes(quiz: Quiz)

    suspend fun fetchQuiz(
        difficulty: Difficulty,
        category: List<Category>,
        limit: Number
    ): Quiz

    suspend fun fetchMetadata(): QuestionsMetadata
}