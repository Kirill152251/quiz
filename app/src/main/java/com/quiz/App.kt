package com.quiz

import android.app.Application
import com.di.ConfigurationScreenDepsStore
import com.quiz.di.AppComponent
import com.quiz.di.DaggerAppComponent

class App: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        ConfigurationScreenDepsStore.deps = appComponent
    }
}