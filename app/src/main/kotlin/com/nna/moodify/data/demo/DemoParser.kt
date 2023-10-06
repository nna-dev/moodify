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

package com.nna.moodify.data.demo

import com.nna.moodify.data.response.NewReleaseAlbumsResponse
import com.nna.moodify.data.response.toAlbum
import com.nna.moodify.domain.model.Album
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okio.buffer
import okio.source
import java.io.InputStream

object DemoParser {
    fun parseNewReleaseAlbumData(inputStream: InputStream): List<Album> {
        val moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<NewReleaseAlbumsResponse> =
            moshi.adapter(NewReleaseAlbumsResponse::class.java)
        val albums = (jsonAdapter.fromJson(inputStream.source().buffer())?.albums?.items
            ?: emptyList())
        return albums.mapNotNull { it.toAlbum() }
    }
}
