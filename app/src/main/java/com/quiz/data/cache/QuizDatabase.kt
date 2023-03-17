package com.quiz.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [QuizEntity::class], version = 1)
@TypeConverters(Converter::class)
abstract class QuizDatabase: RoomDatabase() {
    abstract fun quizDao(): QuizDao
}