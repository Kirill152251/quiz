package com.quiz.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quiz.domain.QuizRepository
import com.quiz.domain.models.AnsweredQuestion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class ResultScreenViewModel @Inject constructor(
    private val repository: QuizRepository
) : ViewModel() {

    private val _likeBtnState: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val likeBtnState = _likeBtnState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.btnLikeIsCheckedFlow().collect {
                _likeBtnState.value = it
            }
        }
    }

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

    fun clearAnsweredQuestionsCache() {
        repository.clearAnsweredQuestionsList()
    }

    fun savedQuizToDb() {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
        val saveTime = LocalDateTime.now().format(formatter)
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveQuizToDb(saveTime)
        }
    }

    fun deleteJustSavedQuizFromDb() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteJustSavedQuizFromDb()
        }
    }
}