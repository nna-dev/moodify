package com.nna.moodify.domain.repositories

interface MusicRepository {
    suspend fun getNewReleaseAlbums()
}