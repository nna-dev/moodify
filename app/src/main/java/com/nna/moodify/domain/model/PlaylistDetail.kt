package com.nna.moodify.domain.model

class PlaylistDetail(
    val playlist: Playlist,
    val tracks: List<Track> = emptyList()
)
