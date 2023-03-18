package com.quiz.di

import com.quiz.data.QuizRepositoryImpl
import com.quiz.data.cache.CacheDataSourceImpl
import com.quiz.data.remote.RemoteDataSourceImpl
import com.quiz.domain.CacheDataSource
import com.quiz.domain.QuizRepository
import com.quiz.domain.RemoteDataSource
import dagger.Binds
import dagger.Module

@Module
interface BindModule {

    @Binds
    fun bindRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    fun bindQuizRepository(impl: QuizRepositoryImpl): QuizRepository

    @Binds
    fun bindCacheDataSource(impl: CacheDataSourceImpl): CacheDataSource
}