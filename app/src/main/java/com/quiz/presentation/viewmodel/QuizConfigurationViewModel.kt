package com.quiz.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.quiz.domain.QuizRepository
import com.quiz.domain.RemoteRepository
import com.quiz.domain.models.Category
import com.quiz.domain.models.Difficulty
import com.quiz.domain.models.Quiz
import com.quiz.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuizConfigurationViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository,
    private val quizRepository: QuizRepository
): ViewModel() {
    suspend fun fetchQuizFlow(
        difficulty: Difficulty,
        number: Int,
        categories: List<Category>
    ): Flow<ApiResult<Quiz>> {
        return remoteRepository.fetchQuizFlow(difficulty, number, categories)
    }

    fun cacheCurrentQuiz(quiz: Quiz) {
        quizRepository.setCurrentQuiz(quiz)
    }

    fun currentQuiz(): Quiz {
        return quizRepository.getCurrentQuiz()
    }
}