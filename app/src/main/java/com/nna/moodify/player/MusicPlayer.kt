package com.nna.moodify.player

interface MusicPlayer {
    sealed class MusicPlayerState {
        object Loading: MusicPlayerState()
        data class Playing(val streamable: Streamable): MusicPlayerState()
        object Error: MusicPlayerState()
        object Idle: MusicPlayerState()
    }

    fun play(streamable: Streamable)
    fun pause()
    fun stop()
    fun resume()
}