package com.nna.moodify.di

import com.nna.moodify.player.ExoMusicPlayer
import com.nna.moodify.player.MusicPlayer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface PlayerModule {

    @Binds
    fun bindMusicPlayer(exoMusicPlayer: ExoMusicPlayer): MusicPlayer
}
