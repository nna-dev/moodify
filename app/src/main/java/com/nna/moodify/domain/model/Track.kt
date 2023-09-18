package com.nna.moodify.domain.model

data class Track(
    val id: String,
    val artists: List<Artist>,
    val name: String,
    val images: SpotifyImages,
    val durationMs: Int,
    val href: String,
    val previewUrl: String? = null,
    val trackNumber: Int,
    val uri: String
)
