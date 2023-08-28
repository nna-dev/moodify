package com.nna.moodify.data.music

import com.nna.moodify.domain.repositories.MusicRepository
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val musicService: MusicService
) : MusicRepository {
    override suspend fun getNewReleaseAlbums() {
        when (val response = musicService.getNewReleaseAlbums("VN")) {
            is ApiResponse.Success -> {
                Timber.d("${response.data}")
            }
            is ApiResponse.Failure -> {
                Timber.e(response.message())
            }
        }
    }
}