package com.quiz.data.cache

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {

    @Insert
    suspend fun insert(quiz: QuizEntity): Int

    @Delete
    suspend fun delete(quiz: QuizEntity)

    @Query("SELECT * FROM QUIZ_DB")
    fun getData(): Flow<List<QuizEntity>>
}