package com.nna.moodify.domain.model

import android.net.Uri
import timber.log.Timber
import java.time.LocalDate

data class Album(
    val id: String,
    val name: String,
    val href: String,
    val externalUrls: Map<String, String>,
    val images: Map<ImageResolution, String>,
    val type: AlbumType,
    val artists: List<Artist>,
    val releaseDate: LocalDate,
    val uri: String,
    val totalTracks: Int = 0
) {
    fun getImageUri(): Uri? {
        val url = images[ImageResolution.getDefault()] ?: images.values.firstOrNull()
        return Uri.parse(url)
    }
}
