package com.quiz.data.cache

import com.quiz.domain.CacheDataSource
import com.quiz.domain.models.SavedQuiz
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CacheDataSourceImpl @Inject constructor(
    private val dao: QuizDao
) : CacheDataSource {

    override suspend fun saveQuiz(quiz: SavedQuiz): Long {
        return dao.insert(quiz.toEntity())
    }

    override fun getQuizList(): Flow<List<SavedQuiz>> {
        return dao.getData().map { list ->
            list.map { quizEntity ->
                quizEntity.toDomain()
            }
        }
    }

    override suspend fun deleteQuiz(quiz: SavedQuiz) {
        dao.delete(quiz.toEntity())
    }
}