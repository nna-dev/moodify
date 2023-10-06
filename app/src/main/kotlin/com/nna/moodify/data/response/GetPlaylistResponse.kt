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

package com.nna.moodify.data.response

import com.nna.moodify.domain.model.Playlist
import com.nna.moodify.domain.model.Track
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetPlaylistResponse(
    val id: String,
    val collaborative: Boolean = false,
    val description: String,
    @Json(name = "external_urls") val externalUrls: Map<String, String> = emptyMap(),
    val followers: FollowerResponse,
    val href: String,
    val images: List<ImageResponse>,
    val name: String,
    @Json(name = "primary_color") val primaryColor: String,
    @Json(name = "snapshot_id") val snapShotId: String,
    val tracks: TracksResponse,
    val type: String,
    val uri: String,
)

fun GetPlaylistResponse.getPlaylist() = Playlist(
    id = this.id,
    collaborative = this.collaborative,
    description = this.description,
    externalUrls = this.externalUrls,
    href = this.href,
    images = this.images.toResolutionImageMap(),
    name = this.name,
    primaryColor = if (this.primaryColor == "#FFFFFF") "#e98df5" else this.primaryColor,
    uri = this.uri,
)

@JsonClass(generateAdapter = true)
data class FollowerResponse(
    val href: String? = null,
    val total: Int
)

@JsonClass(generateAdapter = true)
data class TracksResponse(
    val href: String,
    val items: List<TrackItemsResponse>,
    val limit: Int,
    val next: String? = null,
    val offset: Int,
    val previous: String? = null,
    val total: Int
)

@JsonClass(generateAdapter = true)
data class TrackItemsResponse(
    @Json(name = "added_at") val addedAt: String,
    @Json(name = "added_by") val addedBy: Any,
    @Json(name = "is_local") val isLocal: Boolean = false,
    @Json(name = "primary_color") val primaryColor: String? = null,
    val track: TrackResponse
)

@JsonClass(generateAdapter = true)
data class TrackResponse(
    val album: AlbumResponse,
    val artists: List<ArtistResponse> = emptyList(),
    @Json(name = "available_markets") val availableMarkets: List<String> = emptyList(),
    @Json(name = "disc_number") val discNumber: Int,
    @Json(name = "duration_ms") val durationMs: Int,
    val episode: Boolean = false,
    val explicit: Boolean = false,
    @Json(name = "external_urls") val externalUrls: Map<String, String> = emptyMap(),
    val href: String,
    val id: String,
    @Json(name = "is_local") val isLocal: Boolean = false,
    val name: String,
    val popularity: Int,
    @Json(name = "preview_url") val previewUrl: String? = null,
    val track: Boolean = false,
    @Json(name = "track_number") val trackNumber: Int,
    val type: String,
    val uri: String
)

fun TrackResponse.toTrack() = Track(
    id = this.id,
    name = this.name,
    artists = this.artists.mapNotNull { it.toArtist() },
    images = this.album.images.toResolutionImageMap(),
    durationMs = this.durationMs,
    href = this.href,
    previewUrl = this.previewUrl,
    trackNumber = this.trackNumber,
    uri = this.uri
)
