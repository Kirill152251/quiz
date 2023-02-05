package com.quiz.data.remote

import android.util.Log
import com.quiz.domain.RemoteDataSource
import com.quiz.domain.models.Category
import com.quiz.domain.models.Difficulty
import com.quiz.domain.models.Quiz
import com.quiz.utils.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val service: QuizService
) : RemoteDataSource {
    override suspend fun getQuiz(
        difficulty: Difficulty,
        number: Int,
        categories: List<Category>
    ): Flow<ApiResult<Quiz>> {

        val categoriesQueryString = categories.joinToString(",") {
            it.convertToString()
        }

        return flow {
            try {
                emit(ApiResult.Loading)
                val questions = service.getQuiz(
                    difficulty = difficulty.convertToString(),
                    categories = categoriesQueryString,
                    limit = number
                ).map { it.toDomain() }
                emit(ApiResult.Success(Quiz(questions, number, difficulty, categories)))
            } catch (e: Exception) {
                Log.d("DEBUG_TAG", e.message.toString())
                emit(ApiResult.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }
}