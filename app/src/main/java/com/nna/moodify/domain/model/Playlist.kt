package com.nna.moodify.domain.model

data class Playlist(
    val id: String,
    val collaborative: Boolean,
    val description: String,
    val externalUrls: Map<String, String>,
    val href: String,
    val images: Map<ImageResolution, String>,
    val name: String,
    val primaryColor: String? = null,
    val uri: String
)
