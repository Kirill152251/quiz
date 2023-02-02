package com.quiz.di

import android.app.Application
import com.data.remote.QuizService
import com.di.ConfigurationScreenDeps
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent : ConfigurationScreenDeps {

    override val quizService: QuizService

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}

@Scope
annotation class AppScope