package com.nna.moodify.domain.model

data class Track(
    val id: String,
    val artist: List<Artist>,
    val durationMs: Int,
    val href: String,
    val previewUrl: String,
    val trackNumber: Int,
    val uri: String
)
