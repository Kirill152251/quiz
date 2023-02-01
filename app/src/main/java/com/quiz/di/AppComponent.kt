package com.quiz.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun application(@BindsInstance application: Application): AppComponent
    }
}

@Scope
annotation class AppScope