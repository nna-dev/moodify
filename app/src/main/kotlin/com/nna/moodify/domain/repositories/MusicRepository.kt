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
