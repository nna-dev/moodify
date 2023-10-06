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

import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nna.moodify.domain.model.Playlist
import com.nna.moodify.domain.music.GetNewReleaseUseCase
import com.nna.moodify.domain.music.GetPlaylistsForCountryUseCase
import com.nna.moodify.domain.successOr
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getNewReleaseUseCase: GetNewReleaseUseCase,
    val getPlaylistsForCountryUseCase: GetPlaylistsForCountryUseCase
) : ViewModel() {

    private val newReleaseAlbumsFlow = flow {
        emit(getNewReleaseUseCase(Unit).successOr(emptyList()))
    }

    private val _countryPlaylists = flow {
        emit(getPlaylistsForCountryUseCase(Unit).successOr(emptyMap()))
    }

    val carouselState =
        newReleaseAlbumsFlow.combine(_countryPlaylists) { newReleaseAlbums, countryPlaylists ->
            val carousels = mutableListOf(
                HomeCarousel(
                    "New Release",
                    "New Release",
                    newReleaseAlbums.map { HomeLargeCard.fromAlbum(it) }),
            )
            for ((category, playlists) in countryPlaylists) {
                carousels.add(
                    HomeCarousel(
                        category.id,
                        category.name,
                        playlists.map { it.toCardItem() }
                    )
                )
            }
            carousels
        }.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), 1)

    private fun Playlist.toCardItem() = HomeLargeCard(
        id = this.id,
        title = this.name,
        imageUri = this.images.values.firstOrNull()?.toUri()
    )
}
