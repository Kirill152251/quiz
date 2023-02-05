package com.quiz

import android.app.Application
import android.content.Context
import com.quiz.di.AppComponent
import com.quiz.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        appComponent = DaggerAppComponent.factory().create(this)
        super.onCreate()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }