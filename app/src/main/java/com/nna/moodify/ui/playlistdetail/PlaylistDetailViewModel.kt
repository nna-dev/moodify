package com.nna.moodify.ui.playlistdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nna.moodify.domain.Result
import com.nna.moodify.domain.model.Playlist
import com.nna.moodify.domain.model.Track
import com.nna.moodify.domain.music.GetPlaylistTracksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

sealed class PlaylistDetailState {
    data class Success(val playlist: Playlist, val tracks: List<Track>) : PlaylistDetailState()
    data class Error(val message: String) : PlaylistDetailState()
    object Loading : PlaylistDetailState()
}

@HiltViewModel
class PlaylistDetailViewModel @Inject constructor(
    private val getPlaylistTracksUseCase: GetPlaylistTracksUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val args = PlaylistDetailFragmentArgs.fromSavedStateHandle(savedStateHandle)

    private val _playlistDetail = flow {
        val detail = getPlaylistTracksUseCase(args.playlistId)
        if (detail is Result.Success) {
            emit(PlaylistDetailState.Success(detail.data.playlist, detail.data.tracks))
        } else {
            emit(PlaylistDetailState.Error(""))
        }
    }
    val detailState: StateFlow<PlaylistDetailState> = _playlistDetail.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        PlaylistDetailState.Loading
    )
}
