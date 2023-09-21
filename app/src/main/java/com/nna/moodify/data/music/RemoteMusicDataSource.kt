package com.nna.moodify.data.music

import com.nna.moodify.data.response.*
import com.nna.moodify.domain.model.*
import com.skydoves.sandwich.*
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import timber.log.Timber
import javax.inject.Inject

class RemoteMusicDataSource @Inject constructor(
    private val musicService: MusicService,
) : MusicDataSource {
    override suspend fun getNewReleaseAlbums(): List<Album> {
        when (val response = musicService.getNewReleaseAlbums("VN")) {
            is ApiResponse.Success -> {
                Timber.d("${response.data}")
                return response.data.albums.items.mapNotNull { it.toAlbum() }
            }
            is ApiResponse.Failure -> {
                Timber.e(response.message())
            }
        }
        return emptyList()
    }

    override suspend fun getPlaylistsForCountry(): Map<Category, List<Playlist>> {
        val result = mutableMapOf<Category, List<Playlist>>()
        musicService.getCategoriesForCountry("VN", "vi_VN")
            .suspendOnSuccess {
                val categories = data.categories.items.map { it.toCategory() }
                coroutineScope {
                    val jobs = categories.map { category ->
                        async {
                            musicService.getCategoryPlaylists(category.id, "VN")
                        }
                    }
                    jobs.awaitAll().mapIndexed { index, apiResponse ->
                        val playlists = if (apiResponse is ApiResponse.Success) {
                            apiResponse.data.playlists.items.filterNotNull().map {
                                it.toPlaylist()
                            }
                        } else emptyList()
                        result.put(categories[index], playlists)
                    }
                }
            }
            .suspendOnFailure {
                Timber.e(message())
            }
        return result
    }

    override suspend fun getPlaylist(playlistId: String): PlaylistDetail {
        var result: PlaylistDetail? = null
        musicService.getPlaylist(playlistId, "VN")
            .suspendOnSuccess {
                val playlist = this.data.getPlaylist()
                val tracks = data.tracks.items.map { it.track.toTrack() }
                result = PlaylistDetail(playlist, tracks)
            }
            .suspendOnFailure {
                Timber.e(message())
                throw Exception(this.message())
            }
        return result!!
    }
}
