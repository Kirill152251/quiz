package com.quiz.presentation.view_models

import androidx.lifecycle.ViewModel
import com.quiz.domain.QuizRepository
import javax.inject.Inject

class QuestionScreenViewModel @Inject constructor(
    private val repository: QuizRepository
) : ViewModel() {

    val currentQuestion by lazy {
        repository.getCurrentQuestion()
    }

    val numberOfCurrentQuestion by lazy {
        repository.getNumberOfCurrentQuestion()
    }

    fun setNextQuestion(chosenAnswer: String) {
        repository.setNextQuestion(chosenAnswer)
    }

    fun setCurrentQuestion() {
        repository.setCurrentQuestion()
    }

    fun getQuizSize(): Int = repository.getQuizSize()
}