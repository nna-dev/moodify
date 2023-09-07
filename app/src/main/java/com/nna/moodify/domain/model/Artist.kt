package com.nna.moodify.domain.model

data class Artist(
    val id: String,
    val name: String,
    val href: String,
    val type: String,
    val uri: String,
    val externalUrls: Map<String, String>
)
