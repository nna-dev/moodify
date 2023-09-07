package com.nna.moodify.domain.model

enum class ImageResolution(val width: Int, val height: Int) {
    Small64x64(64, 64),
    Medium300x300(300, 300),
    Medium640x640(640, 640),
    Default(64, 64),
    Unknown(0, 0);

    companion object {
        @JvmStatic
        fun getDefault() = Medium300x300

        @JvmStatic
        fun ofSize(width: Int?, height: Int?): ImageResolution {
            for (resolution in ImageResolution.values()) {
                if (resolution.width == width && resolution.height == height)
                    return resolution
            }
            if (width == null || height == null) return Default
            return Unknown
        }
    }
}