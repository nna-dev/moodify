/*
 * Designed and developed by 2023 nna-dev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
                result = PlaylistDetail(playlist, tracks, data.followers.total)
            }
            .suspendOnFailure {
                Timber.e(message())
                throw Exception(this.message())
            }
        return result!!
    }

    override suspend fun getCategories(limit: Int): List<Category> {
        val result = mutableListOf<Category>()
        musicService.getCategoriesForCountry("VN", "vi_VN", limit)
            .suspendOnSuccess {
                result.addAll(data.categories.items.map { it.toCategory() })
            }
            .suspendOnFailure {
                Timber.e(message())
                throw Exception(this.message())
            }
        return result
    }
}
