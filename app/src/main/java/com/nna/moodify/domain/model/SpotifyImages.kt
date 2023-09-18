package com.nna.moodify.domain.model

import android.net.Uri

typealias SpotifyImages = Map<ImageResolution, String>

fun SpotifyImages.getDefaultImageUri(): Uri? {
    if (this.isEmpty()) return null
    val uriString = if (this.contains(ImageResolution.getDefault()))
        this[ImageResolution.getDefault()]
    else
        this.values.first()
    return Uri.parse(uriString)
}