package com.nna.moodify.di

import com.nna.moodify.player.ExoMusicPlayer
import com.nna.moodify.player.MusicPlayer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface PlayerModule {

    @Binds
    @Singleton
    fun bindMusicPlayer(exoMusicPlayer: ExoMusicPlayer): MusicPlayer
}
