package com.nna.moodify.di

import com.nna.moodify.data.auth.AuthDataSource
import com.nna.moodify.data.auth.AuthRepositoryImpl
import com.nna.moodify.data.auth.AuthSharedPrefDataSource
import com.nna.moodify.data.music.DemoMusicDataSource
import com.nna.moodify.data.music.MusicDataSource
import com.nna.moodify.data.music.MusicRepositoryImpl
import com.nna.moodify.data.music.RemoteMusicDataSource
import com.nna.moodify.domain.repositories.AuthRepository
import com.nna.moodify.domain.repositories.MusicRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
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

    @Binds
    @Singleton
    fun bindMusicRepository(musicRepository: MusicRepositoryImpl): MusicRepository

    @Binds
    @Singleton
    @Named("RemoteMusicDataSource")
    fun bindRemoteMusicDataSource(dataSource: RemoteMusicDataSource): MusicDataSource

    @Binds
    @Singleton
    @Named("DemoMusicDataSource")
    fun bindDemoMusicDataSource(dataSource: DemoMusicDataSource): MusicDataSource
}
