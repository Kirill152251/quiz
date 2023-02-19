package com.quiz.domain

import com.quiz.domain.models.Category
import com.quiz.domain.models.Difficulty
import com.quiz.domain.models.Question
import com.quiz.domain.models.Quiz
import com.quiz.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface QuizRepository {

    fun setCurrentQuiz(quiz: Quiz)
    fun getCurrentQuestion(): Flow<Question>
    fun getNumberOfCurrentQuestion(): Flow<Int>
    fun setNextQuestion(chosenAnswer: String)
    fun setCurrentQuestion()
    fun getQuizSize(): Int

    suspend fun fetchQuizFlow(
        difficulty: Difficulty,
        number: Int,
        categories: List<Category>
    ): Flow<ApiResult<Quiz>>
}