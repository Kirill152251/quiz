package com.quiz.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quiz.domain.CacheRepository
import com.quiz.domain.QuizRepository
import com.quiz.domain.models.AnsweredQuestion
import com.quiz.domain.models.Quiz
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class ResultScreenViewModel @Inject constructor(
    private val quizRepository: QuizRepository,
    private val cacheRepository: CacheRepository
) : ViewModel() {

    fun getAnsweredQuestions(): List<AnsweredQuestion> =
        quizRepository.getAnsweredQuestions()

    fun getNumberOfQuestions(): String = quizRepository.getQuizSize().toString()

    fun getNumberOfCorrectAnswers(): String {
        val list = quizRepository.getAnsweredQuestions()
        var number = 0
        list.forEach {
            if (it.isAnsweredCorrect()) number++
        }
        return number.toString()
    }

    fun savedQuizToDb() {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        val saveTime = LocalDateTime.now().format(formatter)
        viewModelScope.launch(Dispatchers.IO) {
            cacheRepository.saveQuiz(saveTime)
        }
    }

    fun resetQuiz() {
        quizRepository.setCurrentQuiz(Quiz.emptyQuiz)
    }
}