package com.nna.moodify.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artist(
    val id: String,
    val name: String,
    val href: String,
    val type: String,
    val uri: String,
    val externalUrls: Map<String, String>
): Parcelable
