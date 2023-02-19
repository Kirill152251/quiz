package com.quiz.data

import com.quiz.domain.QuizRepository
import com.quiz.domain.RemoteDataSource
import com.quiz.domain.models.Category
import com.quiz.domain.models.Difficulty
import com.quiz.domain.models.Question
import com.quiz.domain.models.Quiz
import com.quiz.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : QuizRepository {

    private lateinit var currentQuiz: Quiz
    private val numberOfCurrentQuestion = MutableStateFlow(1)
    private val currentQuestion =
        MutableStateFlow(Question.emptyQuestion)

    override fun getCurrentQuestion(): Flow<Question> {
        return currentQuestion
    }

    override fun getNumberOfCurrentQuestion(): Flow<Int> {
        return numberOfCurrentQuestion
    }

    override fun setNextQuestion() {
        numberOfCurrentQuestion.value += 1
        currentQuestion.value = currentQuiz.questions[numberOfCurrentQuestion.value - 1]
    }

    override fun setCurrentQuestion() {
        currentQuestion.value = currentQuiz.questions[numberOfCurrentQuestion.value - 1]
    }

    override fun getQuizSize(): Int {
        return currentQuiz.questions.size
    }

    override fun setCurrentQuiz(quiz: Quiz) {
        currentQuiz = quiz
    }

    override suspend fun fetchQuizFlow(
        difficulty: Difficulty,
        number: Int,
        categories: List<Category>
    ): Flow<ApiResult<Quiz>> {
        return remoteDataSource.getQuiz(difficulty, number, categories)
    }
}