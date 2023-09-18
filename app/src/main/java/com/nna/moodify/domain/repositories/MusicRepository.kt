package com.nna.moodify.domain.repositories

import com.nna.moodify.domain.model.Album
import com.nna.moodify.domain.model.Category
import com.nna.moodify.domain.model.Playlist
import com.nna.moodify.domain.model.Track

interface MusicRepository {
    suspend fun getNewReleaseAlbums(): List<Album>
    suspend fun getPlaylistsForCountry(): Map<Category, List<Playlist>>
    suspend fun getPlaylistTracks(playlistId: String): List<Track>
}