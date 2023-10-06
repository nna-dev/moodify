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
