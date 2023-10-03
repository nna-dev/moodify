package com.nna.moodify.player

interface Streamable {
    val info: StreamableInfo
}

data class StreamableInfo(
    val url: String,
    val imageUrl: String? = null,
    val title: String,
    val subTitle: String? = null
)