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