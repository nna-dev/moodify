package com.nna.moodify.domain.music

import com.nna.moodify.di.IoDispatcher
import com.nna.moodify.domain.UseCase
import com.nna.moodify.domain.model.Category
import com.nna.moodify.domain.model.Playlist
import com.nna.moodify.domain.repositories.MusicRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetPlaylistsForCountryUseCase @Inject constructor(
    private val musicRepository: MusicRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : UseCase<Unit, Map<Category, List<Playlist>>>(ioDispatcher) {
    override suspend fun execute(parameters: Unit): Map<Category, List<Playlist>> {
        return musicRepository.getPlaylistsForCountry()
    }
}