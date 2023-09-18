package com.nna.moodify.data.music

import com.nna.moodify.domain.model.Album
import com.nna.moodify.domain.model.Category
import com.nna.moodify.domain.model.Playlist
import com.nna.moodify.domain.model.Track

interface MusicDataSource {
    suspend fun getNewReleaseAlbums(): List<Album>
    suspend fun getPlaylistsForCountry(): Map<Category, List<Playlist>>
    suspend fun getPlaylist(playlistId: String): List<Track>
}