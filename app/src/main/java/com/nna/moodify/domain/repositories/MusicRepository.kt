package com.nna.moodify.domain.repositories

import com.nna.moodify.domain.model.*

interface MusicRepository {
    suspend fun getNewReleaseAlbums(): List<Album>
    suspend fun getPlaylistsForCountry(): Map<Category, List<Playlist>>
    suspend fun getPlaylistDetail(playlistId: String): PlaylistDetail
}