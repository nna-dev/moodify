package com.nna.moodify.data.music

import com.nna.moodify.domain.model.*

interface MusicDataSource {
    suspend fun getNewReleaseAlbums(): List<Album>
    suspend fun getPlaylistsForCountry(): Map<Category, List<Playlist>>
    suspend fun getPlaylist(playlistId: String): PlaylistDetail
    suspend fun getCategories(limit: Int): List<Category>
}