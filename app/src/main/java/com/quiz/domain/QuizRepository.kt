package com.quiz.domain

import com.quiz.domain.models.*
import com.quiz.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface QuizRepository {

    fun setCurrentQuiz(quiz: Quiz)
    fun getCurrentQuiz(): Quiz
    fun getCurrentQuestion(): Flow<Question>
    fun getNumberOfCurrentQuestion(): Flow<Int>
    fun setNextQuestion(chosenAnswer: String)
    fun setCurrentQuestion()
    fun getQuizSize(): Int
    fun getAnsweredQuestions(): List<AnsweredQuestion>
    suspend fun deleteQuizFromDb(quiz: SavedQuiz)
    fun getSavedQuizFlowFromDb(): Flow<List<SavedQuiz>>
    suspend fun getSavedQuizListFromDb(): List<SavedQuiz>
    suspend fun saveQuizToDb(saveTime: String): Long
}