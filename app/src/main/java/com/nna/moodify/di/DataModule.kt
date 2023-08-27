package com.nna.moodify.di

import com.nna.moodify.data.auth.AuthDataSource
import com.nna.moodify.data.auth.AuthRepositoryImpl
import com.nna.moodify.data.auth.AuthSharedPrefDataSource
import com.nna.moodify.domain.auth.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface DataModule {
    @Binds
    @Singleton
    fun bindAuthDataSource(dataSource: AuthSharedPrefDataSource): AuthDataSource

    @Binds
    @Singleton
    fun bindAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository
}