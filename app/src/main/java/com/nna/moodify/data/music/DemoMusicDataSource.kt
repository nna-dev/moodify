package com.nna.moodify.data.music

import android.content.Context
import com.nna.moodify.data.demo.DemoParser
import com.nna.moodify.data.demo.DemoResourcePath
import com.nna.moodify.domain.model.Album
import com.nna.moodify.domain.model.Category
import com.nna.moodify.domain.model.Playlist
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DemoMusicDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
) : MusicDataSource {
    override suspend fun getNewReleaseAlbums(): List<Album> {
        return context.assets.open(DemoResourcePath.GET_NEW_RELEASE_ALBUMS_ASSET).let {
            DemoParser.parseNewReleaseAlbumData(it)
        }
    }

    override suspend fun getPlaylistsForCountry(): Map<Category, List<Playlist>> {
        return mapOf()
    }
}