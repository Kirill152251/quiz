package com.quiz.presentation.view_models

import androidx.lifecycle.ViewModel
import com.quiz.domain.QuizRepository
import com.quiz.domain.models.AnsweredQuestion
import javax.inject.Inject

class ResultScreenViewModel @Inject constructor(
    private val repository: QuizRepository
) : ViewModel() {

    fun getAnsweredQuestions(): List<AnsweredQuestion> =
        repository.getAnsweredQuestions()

    fun getNumberOfQuestions(): String = repository.getQuizSize().toString()

    fun getNumberOfCorrectAnswers(): String {
        val list = repository.getAnsweredQuestions()
        var number = 0
        list.forEach {
            if (it.isAnsweredCorrect()) number++
        }
        return number.toString()
    }
}