package com.nna.moodify.ui.playlistdetail

import android.net.Uri
import com.nna.moodify.domain.model.Track
import com.nna.moodify.domain.model.getDefaultImageUri

data class TrackViewModel(
    val trackId: String,
    val imageUri: Uri?,
    val title: String,
    val artists: String
) {
    companion object {
        @JvmStatic
        fun fromTrack(track: Track): TrackViewModel {
            return TrackViewModel(
                trackId = track.id,
                imageUri = track.images.getDefaultImageUri(),
                title = track.name,
                artists = track.artists.joinToString(", ") { it.name }
            )
        }
    }
}