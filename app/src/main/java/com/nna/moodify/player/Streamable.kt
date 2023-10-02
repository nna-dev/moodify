package com.nna.moodify.player

interface Streamable {
    val info: StreamableInfo
}

data class StreamableInfo(
    val url: String? = null,
    val imageUrl: String? = null,
    val title: String,
    val subTitle: String? = null
)