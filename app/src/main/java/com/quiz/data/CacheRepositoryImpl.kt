package com.quiz.data

import com.quiz.domain.CacheDataSource
import com.quiz.domain.CacheRepository
import com.quiz.domain.QuizRepository
import com.quiz.domain.models.SavedQuiz
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CacheRepositoryImpl @Inject constructor(
    private val cacheDataSource: CacheDataSource,
    private val quizRepository: QuizRepository
) : CacheRepository {

    override suspend fun deleteQuiz(quiz: SavedQuiz) {
        cacheDataSource.deleteQuiz(quiz)
    }

    override fun getSavedQuizFlow(): Flow<List<SavedQuiz>> =
        cacheDataSource.getQuizFlow()


    override suspend fun getSavedQuizList(): List<SavedQuiz> =
        cacheDataSource.getQuizList()

    override suspend fun saveQuiz(saveTime: String): Long {
        val currentQuiz = quizRepository.getCurrentQuiz()
        return cacheDataSource.saveQuiz(currentQuiz.toSavedQuiz(saveTime))
    }
}