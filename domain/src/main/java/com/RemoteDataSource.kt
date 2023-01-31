package com

import com.models.Question
import com.models.QuestionsMetadata
import com.utils.ApiResult

interface RemoteDataSource {

    suspend fun getMetadata(): QuestionsMetadata

    suspend fun getQuestions(
        number: Int,
        difficulty: String,
        category: List<String>
    ): ApiResult<List<Question>>
}