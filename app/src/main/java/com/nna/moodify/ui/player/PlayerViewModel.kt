package com.nna.moodify.ui.player

import androidx.lifecycle.ViewModel
import com.nna.moodify.player.MusicPlayer
import com.nna.moodify.player.Streamable
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val musicPlayer: MusicPlayer
) : ViewModel() {

    fun playStreamable(streamable: Streamable) {
        musicPlayer.play(streamable)
    }
}
