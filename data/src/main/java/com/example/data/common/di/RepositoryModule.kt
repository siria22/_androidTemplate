package com.example.data.common.di

import com.example.data.remote.local.repository.ExampleRepositoryImpl
import com.example.data.remote.network.repository.RandomUserRepositoryImpl
import com.example.domain.repository.ExampleRepository
import com.example.domain.repository.RandomUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindExampleRepository(
        impl: ExampleRepositoryImpl
    ): ExampleRepository

    @Binds
    @Singleton
    abstract fun bindServerTimeRepository(
        impl: RandomUserRepositoryImpl
    ): RandomUserRepository
}