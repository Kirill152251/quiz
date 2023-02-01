package com.data.remote

import com.data.remote.model.MetaDataResponse
import com.data.remote.model.QuestionItem
import retrofit2.http.GET
import retrofit2.http.Query

//https://the-trivia-api.com/api/metadata
//https://the-trivia-api.com/api/questions?categories=geography,food_and_drink,music&limit=5&difficulty=medium
interface QuizService {

    @GET("/metadata")
    suspend fun getMetadata(): MetaDataResponse

    @GET("/questions")
    suspend fun getQuiz(
        @Query("categories") categories: String,
        @Query("limit") limit: Int,
        @Query("difficulty") difficulty: String
    ): List<QuestionItem>
}