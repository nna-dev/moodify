package com.nna.moodify.domain.model

data class Category(
    val id: String,
    val href: String,
    val name: String,
    val icons: Map<ImageResolution, String>
)
