package com.quiz.di

import com.data.remote.QuizService
import dagger.Module
import dagger.Provides

@Module
object AppModule {

    @AppScope
    @Provides
    fun provideQuizService() = QuizService()
}