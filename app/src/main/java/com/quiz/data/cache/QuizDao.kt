package com.quiz.data.cache

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(quiz: QuizEntity): Long

    @Delete
    suspend fun delete(quiz: QuizEntity)

    @Query("SELECT * FROM QUIZ_DB")
    fun getDataFlow(): Flow<List<QuizEntity>>

    @Query("SELECT * FROM QUIZ_DB")
    suspend fun getDataList(): List<QuizEntity>
}