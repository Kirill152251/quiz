package com.quiz.di

import android.content.Context
import com.quiz.presentation.LikedQuizFragment
import com.quiz.presentation.QuestionScreen
import com.quiz.presentation.QuizConfigurationScreen
import com.quiz.presentation.ResultScreen
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: QuizConfigurationScreen)
    fun inject(fragment: QuestionScreen)
    fun inject(fragment: ResultScreen)
    fun inject(fragment: LikedQuizFragment)
}