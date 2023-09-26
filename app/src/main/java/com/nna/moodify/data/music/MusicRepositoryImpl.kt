package com.nna.moodify.data.music

import com.nna.moodify.domain.model.*
import com.nna.moodify.domain.repositories.MusicRepository
import javax.inject.Inject
import javax.inject.Named

class MusicRepositoryImpl @Inject constructor(
    @Named("DemoMusicDataSource") private val demoDataSource: MusicDataSource,
    @Named("RemoteMusicDataSource") private val remoteDataSource: MusicDataSource
) : MusicRepository {
    override suspend fun getNewReleaseAlbums(): List<Album> {
        return demoDataSource.getNewReleaseAlbums()
    }

    override suspend fun getPlaylistsForCountry(): Map<Category, List<Playlist>> {
        return remoteDataSource.getPlaylistsForCountry()
    }

    override suspend fun getPlaylistDetail(playlistId: String): PlaylistDetail {
        return remoteDataSource.getPlaylist(playlistId)
    }

    override suspend fun getCategories(limit: Int): List<Category> {
        return remoteDataSource.getCategories(limit)
    }
}