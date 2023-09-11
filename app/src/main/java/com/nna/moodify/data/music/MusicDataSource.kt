package com.nna.moodify.data.music

import com.nna.moodify.domain.model.Album
import com.nna.moodify.domain.model.Category
import com.nna.moodify.domain.model.Playlist

interface MusicDataSource {
    suspend fun getNewReleaseAlbums(): List<Album>
    suspend fun getPlaylistsForCountry(): Map<Category, List<Playlist>>
}