package com.quiz.di

import com.quiz.data.remote.RemoteDataSourceImpl
import com.quiz.domain.RemoteDataSource
import dagger.Binds
import dagger.Module

@Module
interface BindModule {

    @Binds
    fun bindRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource
}