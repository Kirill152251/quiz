package com.quiz.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quiz.domain.CacheRepository
import com.quiz.domain.QuizRepository
import com.quiz.domain.models.SavedQuiz
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LikedQuizScreenViewModel @Inject constructor(
    private val cacheRepository: CacheRepository,
    private val quizRepository: QuizRepository
) : ViewModel() {
    private val _state: MutableStateFlow<LikedQuizScreenState> =
        MutableStateFlow(LikedQuizScreenState.Idle)
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<LikedQuizScreenEvent>()
    private val event = _event.asSharedFlow()

    init {
        handleEvent()
        viewModelScope.launch {
            cacheRepository.getSavedQuizFlow().collect { quizList ->
                if (quizList.isNotEmpty()) {
                    _state.update { LikedQuizScreenState.LikedQuizList(quizList) }
                } else {
                    _state.update { LikedQuizScreenState.EmptyList }
                }
            }
        }
    }

    private fun handleEvent() {
        viewModelScope.launch {
            event.collect { event ->
                when (event) {
                    is LikedQuizScreenEvent.DeleteItem -> {
                        withContext(Dispatchers.IO) {
                            cacheRepository.deleteQuiz(event.quiz)
                        }
                    }
                    is LikedQuizScreenEvent.SetupChosenQuiz -> {
                        quizRepository.setCurrentQuiz(event.quiz.toDomain())
                    }
                }
            }
        }
    }

    fun setEvent(event: LikedQuizScreenEvent) {
        val newEvent = event
        viewModelScope.launch { _event.emit(newEvent) }
    }
}

sealed class LikedQuizScreenState {
    object Idle : LikedQuizScreenState()
    object EmptyList : LikedQuizScreenState()
    data class LikedQuizList(val quizList: List<SavedQuiz>) : LikedQuizScreenState()
}

sealed class LikedQuizScreenEvent {
    data class DeleteItem(val quiz: SavedQuiz) : LikedQuizScreenEvent()
    data class SetupChosenQuiz(val quiz: SavedQuiz) : LikedQuizScreenEvent()
}