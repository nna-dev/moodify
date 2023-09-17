package com.nna.moodify.data.response

import com.nna.moodify.domain.model.Playlist
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GetCategoryPlaylistsResponse(
    val playlists: PlaylistsResponse
)

@JsonClass(generateAdapter = true)
data class PlaylistsResponse(
    val href: String,
    val items: List<PlaylistResponse>,
    val limit: Int,
    val next: String?,
    val offset: Int,
    val previous: String?,
    val total: Int
)

@JsonClass(generateAdapter = true)
data class PlaylistResponse(
    val collaborative: Boolean,
    val description: String,
    @Json(name = "external_urls") val externalUrls: Map<String, String>,
    val href: String,
    val id: String,
    val images: List<ImageResponse>,
    val name: String,
    @Json(name = "primary_color") val primaryColor: String?,
    val public: String?,
    val type: String,
    val uri: String
)

fun PlaylistResponse.toPlaylist() = Playlist(
    id = this.id,
    collaborative = this.collaborative,
    description = this.description,
    externalUrls = this.externalUrls,
    href = this.href,
    images = this.images.toResolutionImageMap(),
    name = this.name,
    primaryColor = this.primaryColor,
    uri = this.uri
)