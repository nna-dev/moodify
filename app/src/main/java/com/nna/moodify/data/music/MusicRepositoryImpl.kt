package com.nna.moodify.data.music

import com.nna.moodify.domain.model.Album
import com.nna.moodify.domain.model.Category
import com.nna.moodify.domain.model.Playlist
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
}