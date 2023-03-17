package com.quiz.di

import android.content.Context
import androidx.room.Room
import com.quiz.data.cache.Converter
import com.quiz.data.cache.QuizDao
import com.quiz.data.cache.QuizDatabase
import com.quiz.data.remote.QuizService
import com.quiz.utils.QUIZ_DATABASE
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [BindModule::class, ViewModelsModule::class])
object AppModule {

    @Provides
    @Singleton
    fun provideQuizService() = QuizService()

    @Provides
    @Singleton
    fun provideDatabase(context: Context): QuizDatabase =
        Room.databaseBuilder(context, QuizDatabase::class.java, QUIZ_DATABASE)
            .addTypeConverter(Converter())
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideDao(db: QuizDatabase): QuizDao = db.quizDao()
}