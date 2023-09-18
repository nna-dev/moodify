package com.nna.moodify.ui.playlistdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nna.moodify.domain.model.Track
import com.nna.moodify.domain.music.GetPlaylistTracksUseCase
import com.nna.moodify.domain.successOr
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class PlaylistDetailViewModel @Inject constructor(
    private val getPlaylistTracksUseCase: GetPlaylistTracksUseCase
) : ViewModel() {
    private val _tracks = flow<List<Track>> {
        emit(getPlaylistTracksUseCase("37i9dQZF1DX5HzXEElAlcz").successOr(emptyList()))
    }
    val tracks = _tracks.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
}
