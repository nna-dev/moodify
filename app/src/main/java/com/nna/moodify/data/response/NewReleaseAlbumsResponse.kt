package com.nna.moodify.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

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
    @Json(name = "available_markets") val availableMarkets: List<String>,
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

@JsonClass(generateAdapter = true)
data class ImageResponse(
    val url: String,
    val height: Int,
    val width: Int
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

//typealias ExternalUrls = Map<String, String>