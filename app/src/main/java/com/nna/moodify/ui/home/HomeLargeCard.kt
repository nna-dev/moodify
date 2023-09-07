package com.nna.moodify.ui.home

import android.net.Uri
import com.nna.moodify.domain.model.Album

data class HomeLargeCard(
    val id: String,
    val title: String,
    val imageUri: Uri?
) {
    companion object {
        @JvmStatic
        fun fromAlbum(album: Album) = HomeLargeCard(
            id = album.id,
            title = album.name,
            imageUri = album.getImageUri()
        )
    }
}
