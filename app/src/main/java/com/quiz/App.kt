package com.quiz

import android.app.Application
import com.quiz.di.AppComponent
import com.quiz.di.DaggerAppComponent

class App: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().application(this)
    }

    override fun onCreate() {
        super.onCreate()
    }
}