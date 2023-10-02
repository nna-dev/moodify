package com.nna.moodify.player

data class MusicStreamable(override val info: StreamableInfo) : Streamable {
    companion object {
        fun of(url: String, title: String): MusicStreamable {
            val info = StreamableInfo(
                url = url,
                title = title,
            )
            return MusicStreamable(info)
        }
    }
}
