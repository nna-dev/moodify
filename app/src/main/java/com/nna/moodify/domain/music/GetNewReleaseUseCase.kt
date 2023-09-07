package com.nna.moodify.domain.music

import com.nna.moodify.di.IoDispatcher
import com.nna.moodify.domain.UseCase
import com.nna.moodify.domain.model.Album
import com.nna.moodify.domain.repositories.MusicRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetNewReleaseUseCase @Inject constructor(
    private val musicRepository: MusicRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): UseCase<Unit, List<Album>>(dispatcher) {
    override suspend fun execute(parameters: Unit): List<Album> {
        return musicRepository.getNewReleaseAlbums()
    }
}