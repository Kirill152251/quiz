package com.quiz.data

import com.quiz.domain.CacheDataSource
import com.quiz.domain.QuizRepository
import com.quiz.domain.RemoteDataSource
import com.quiz.domain.models.*
import com.quiz.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val cacheDataSource: CacheDataSource
) : QuizRepository {

    private lateinit var currentQuiz: Quiz
    private var lastSavedQuiz: SavedQuiz? = null
    private val numberOfCurrentQuestion = MutableStateFlow(1)
    private val currentQuestion =
        MutableStateFlow(Question.emptyQuestion)
    private val answeredQuestionsList = mutableListOf<AnsweredQuestion>()

    override fun getCurrentQuestion(): Flow<Question> {
        return currentQuestion
    }

    override fun getNumberOfCurrentQuestion(): Flow<Int> {
        return numberOfCurrentQuestion
    }

    override fun setNextQuestion(chosenAnswer: String) {
        val answeredQuestion = AnsweredQuestion(
            chosenAnswer = chosenAnswer,
            correctAnswer = currentQuestion.value.correctAnswer,
            allAnswers = currentQuestion.value.shuffledAnswers,
            questionText = currentQuestion.value.question
        )
        answeredQuestionsList.add(answeredQuestion)
        if (numberOfCurrentQuestion.value == currentQuiz.questions.size) return
        numberOfCurrentQuestion.value += 1
        currentQuestion.value = currentQuiz.questions[numberOfCurrentQuestion.value - 1]
    }

    override fun setCurrentQuestion() {
        currentQuestion.value = currentQuiz.questions[numberOfCurrentQuestion.value - 1]
    }

    override fun getQuizSize(): Int {
        return currentQuiz.questions.size
    }

    override fun getAnsweredQuestions(): List<AnsweredQuestion> {
        return answeredQuestionsList
    }

    override fun clearAnsweredQuestionsList() {
        answeredQuestionsList.clear()
        numberOfCurrentQuestion.value = 1
    }

    override suspend fun deleteQuizFromDb(quiz: SavedQuiz) {
        cacheDataSource.deleteQuiz(quiz)
    }

    override suspend fun deleteJustSavedQuizFromDb() {
        lastSavedQuiz?.let {
            cacheDataSource.deleteQuiz(it)
        }
    }

    override fun getSavedQuizFromDb(): Flow<List<SavedQuiz>> {
        return cacheDataSource.getQuizList()
    }

    override suspend fun saveQuizToDb(saveTime: String): Long {
        lastSavedQuiz = currentQuiz.toSavedQuiz(saveTime)
        return cacheDataSource.saveQuiz(lastSavedQuiz!!)
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