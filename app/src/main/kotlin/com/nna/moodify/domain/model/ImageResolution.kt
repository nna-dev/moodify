/*
 * Designed and developed by 2023 nna-dev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
