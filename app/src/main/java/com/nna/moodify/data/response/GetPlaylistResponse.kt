package com.nna.moodify.data.response

import com.nna.moodify.domain.model.Track
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetPlaylistResponse(
    val id: String,
    val collaborative: Boolean = false,
    val description: String,
    @Json(name = "external_urls") val externalUrls: Map<String, String> = emptyMap(),
    val href: String,
    val images: List<ImageResponse>,
    val name: String,
    @Json(name = "primary_color") val primaryColor: String,
    @Json(name = "snapshot_id") val snapShotId: String,
    val tracks: TracksResponse
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
