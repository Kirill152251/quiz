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

    private  var currentQuiz = Quiz.emptyQuiz
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

    override suspend fun deleteQuizFromDb(quiz: SavedQuiz) {
        cacheDataSource.deleteQuiz(quiz)
    }

    override fun getSavedQuizFlowFromDb(): Flow<List<SavedQuiz>> {
        return cacheDataSource.getQuizFlow()
    }

    override suspend fun getSavedQuizListFromDb(): List<SavedQuiz> {
        return cacheDataSource.getQuizList()
    }

    override suspend fun saveQuizToDb(saveTime: String): Long {
        return cacheDataSource.saveQuiz(currentQuiz.toSavedQuiz(saveTime))
    }

    override fun setCurrentQuiz(quiz: Quiz) {
        answeredQuestionsList.clear()
        numberOfCurrentQuestion.value = 1
        currentQuiz = quiz
    }
    override fun getCurrentQuiz(): Quiz {
        return currentQuiz
    }

    override suspend fun fetchQuizFlow(
        difficulty: Difficulty,
        number: Int,
        categories: List<Category>
    ): Flow<ApiResult<Quiz>> {
        return remoteDataSource.getQuiz(difficulty, number, categories)
    }
}