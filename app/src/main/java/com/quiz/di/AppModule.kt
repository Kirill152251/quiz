package com.quiz.di

import com.quiz.data.remote.QuizService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @Singleton
    fun provideQuizService() = QuizService()
}