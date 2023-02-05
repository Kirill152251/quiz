package com.quiz.di

import android.content.Context
import com.quiz.presentation.QuizConfigurationScreen
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: QuizConfigurationScreen)
}