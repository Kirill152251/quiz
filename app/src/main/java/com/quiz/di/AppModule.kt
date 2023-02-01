package com.quiz.di

import com.di.NetworkModule
import dagger.Module

@Module(includes = [NetworkModule::class])
object AppModule