package com.nna.moodify.domain.repositories

import com.nna.moodify.domain.model.Album
import com.nna.moodify.domain.model.Category
import com.nna.moodify.domain.model.Playlist
import com.nna.moodify.domain.model.PlaylistDetail

interface MusicRepository {
    suspend fun getNewReleaseAlbums(): List<Album>
    suspend fun getPlaylistsForCountry(): Map<Category, List<Playlist>>
    suspend fun getPlaylistDetail(playlistId: String): PlaylistDetail
    suspend fun getCategories(limit: Int): List<Category>
}