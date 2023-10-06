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
