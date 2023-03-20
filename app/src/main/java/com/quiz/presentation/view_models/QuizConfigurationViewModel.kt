package com.quiz.presentation.view_models

import androidx.lifecycle.ViewModel
import com.quiz.domain.QuizRepository
import com.quiz.domain.models.Category
import com.quiz.domain.models.Difficulty
import com.quiz.domain.models.Quiz
import com.quiz.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuizConfigurationViewModel @Inject constructor(
    private val repository: QuizRepository
): ViewModel() {
    suspend fun fetchQuizFlow(
        difficulty: Difficulty,
        number: Int,
        categories: List<Category>
    ): Flow<ApiResult<Quiz>> {
        return repository.fetchQuizFlow(difficulty, number, categories)
    }

    fun cacheCurrentQuiz(quiz: Quiz) {
        repository.setCurrentQuiz(quiz)
    }

    fun currentQuiz(): Quiz {
        return repository.getCurrentQuiz()
    }
}