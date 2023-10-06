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

import com.nna.moodify.domain.model.Album
import com.nna.moodify.domain.model.AlbumType
import com.nna.moodify.domain.model.Artist
import com.nna.moodify.domain.model.ImageResolution
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate

@JsonClass(generateAdapter = true)
data class NewReleaseAlbumsResponse(
    @Json(name = "albums") val albums: AlbumsResponse
)

@JsonClass(generateAdapter = true)
data class AlbumsResponse(
    val href: String,
    val limit: Int,
    val next: String? = null,
    val offset: Int,
    val previous: String? = null,
    val total: Int,
    val items: List<AlbumResponse>
)

@JsonClass(generateAdapter = true)
data class AlbumResponse(
    @Json(name = "album_type") val albumType: String,
    @Json(name = "artists") val artists: List<ArtistResponse>,
    @Json(name = "available_markets") val availableMarkets: List<String> = emptyList(),
    @Json(name = "external_urls") val externalUrls: Map<String, String>,
    val href: String,
    val id: String,
    val images: List<ImageResponse>,
    val name: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "release_date_precision") val releaseDatePrecision: String,
    @Json(name = "total_tracks") val totalTracks: Int,
    val type: String,
    val uri: String
)

fun AlbumResponse.toAlbum(): Album? {
    try {
        val type = AlbumType.ofName(this.type) ?: return null
        return Album(
            id = this.id,
            name = this.name,
            href = this.href,
            externalUrls = externalUrls,
            images = this.images.toResolutionImageMap(),
            type = type,
            artists = artists.mapNotNull { it.toArtist() },
            releaseDate = LocalDate.parse(this.releaseDate),
            uri = this.uri,
        )
    } catch (e: Exception) {
        return null
    }
}

@JsonClass(generateAdapter = true)
data class ImageResponse(
    val url: String,
    val height: Int?,
    val width: Int?
)

@JsonClass(generateAdapter = true)
data class ArtistResponse(
    @Json(name = "external_urls") val externalUrls: Map<String, String>,
    val href: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String
)

fun ArtistResponse.toArtist(): Artist? {
    return Artist(
        id = this.id,
        name = this.name,
        href = this.href,
        type = this.type,
        uri = this.uri,
        externalUrls = externalUrls
    )
}

fun List<ImageResponse>.toResolutionImageMap(): Map<ImageResolution, String> =
    map {
        ImageResolution.ofSize(it.width, it.height) to it.url
    }.filter { it.first != ImageResolution.Unknown }.toMap()

//typealias ExternalUrls = Map<String, String>
