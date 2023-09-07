package com.nna.moodify.domain.model

enum class AlbumType(val typeName: String) {
    Single("single"),
    Album("album"),
    Unknown("unknown");

    companion object {
        @JvmStatic
        fun ofName(name: String): AlbumType? {
            for (type in AlbumType.values()) {
                if (type.typeName == name) return type
            }
            return null
        }

    }
}
