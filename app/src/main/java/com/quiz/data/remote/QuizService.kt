package com.quiz.data.remote

import com.quiz.data.remote.model.MetaDataResponse
import com.quiz.data.remote.model.QuestionItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://the-trivia-api.com/api/"

interface QuizService {

    @GET("metadata")
    suspend fun getMetadata(): MetaDataResponse

    @GET("questions")
    suspend fun getQuiz(
        @Query("categories") categories: String,
        @Query("limit") limit: Int,
        @Query("difficulty") difficulty: String
    ): List<QuestionItem>
}

fun QuizService(): QuizService {
    val loggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(QuizService::class.java)
}