package com.quiz.domain

import com.quiz.domain.models.*
import com.quiz.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface QuizRepository {

    fun setCurrentQuiz(quiz: Quiz)
    fun getCurrentQuestion(): Flow<Question>
    fun getNumberOfCurrentQuestion(): Flow<Int>
    fun setNextQuestion(chosenAnswer: String)
    fun setCurrentQuestion()
    fun getQuizSize(): Int
    fun getAnsweredQuestions(): List<AnsweredQuestion>
    fun clearAnsweredQuestionsList()
    suspend fun deleteQuizFromDb(quiz: SavedQuiz)
    suspend fun deleteJustSavedQuizFromDb()
    fun getSavedQuizFromDb(): Flow<List<SavedQuiz>>
    suspend fun saveQuizToDb(saveTime: String): Long
    fun btnLikeIsCheckedFlow(): Flow<Boolean>

    suspend fun fetchQuizFlow(
        difficulty: Difficulty,
        number: Int,
        categories: List<Category>
    ): Flow<ApiResult<Quiz>>
}