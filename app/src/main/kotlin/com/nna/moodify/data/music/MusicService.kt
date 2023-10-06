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

import com.nna.moodify.data.response.GetCategoriesResponse
import com.nna.moodify.data.response.GetCategoryPlaylistsResponse
import com.nna.moodify.data.response.GetPlaylistResponse
import com.nna.moodify.data.response.NewReleaseAlbumsResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MusicService {
    @GET("/v1/browse/new-releases")
    suspend fun getNewReleaseAlbums(
        @Query("country") country: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ApiResponse<NewReleaseAlbumsResponse>

    @GET("/v1/browse/categories")
    suspend fun getCategoriesForCountry(
        @Query("country") country: String,
        @Query("locale") locale: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ApiResponse<GetCategoriesResponse>

    @GET("/v1/browse/categories/{category_id}/playlists")
    suspend fun getCategoryPlaylists(
        @Path("category_id") categoryId: String,
        @Query("country") country: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ApiResponse<GetCategoryPlaylistsResponse>

    @GET("/v1/playlists/{playlist_id}")
    suspend fun getPlaylist(
        @Path("playlist_id") playlistId: String,
        @Query("market") market: String,
    ): ApiResponse<GetPlaylistResponse>
}
