package com.quiz.domain

import com.quiz.domain.models.AnsweredQuestion
import com.quiz.domain.models.Question
import com.quiz.domain.models.Quiz
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
}