package com.quiz.data

import com.quiz.domain.QuizRepository
import com.quiz.domain.RemoteDataSource
import com.quiz.domain.models.Category
import com.quiz.domain.models.Difficulty
import com.quiz.domain.models.Question
import com.quiz.domain.models.Quiz
import com.quiz.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : QuizRepository {

    private var currentQuiz: Quiz? = null
    private var currentQuestion: Question? = null

    override fun setCurrentQuiz(quiz: Quiz) {
        currentQuiz = quiz
    }

    override fun setCurrentQuestion(question: Question) {
        currentQuestion = question
    }

    override fun getCurrentQuiz(): Quiz? {
        return currentQuiz
    }

    override fun getCurrentQuestion(): Question? {
        return currentQuestion
    }

    override suspend fun fetchQuizFlow(
        difficulty: Difficulty,
        number: Int,
        categories: List<Category>
    ): Flow<ApiResult<Quiz>> {
        return remoteDataSource.getQuiz(difficulty, number, categories)
    }
}