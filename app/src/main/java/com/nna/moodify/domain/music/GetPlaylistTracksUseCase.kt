package com.nna.moodify.domain.music

import com.nna.moodify.di.IoDispatcher
import com.nna.moodify.domain.UseCase
import com.nna.moodify.domain.model.Track
import com.nna.moodify.domain.repositories.MusicRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetPlaylistTracksUseCase @Inject constructor(
    private val musicRepository: MusicRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): UseCase<String, List<Track>>(dispatcher) {
    override suspend fun execute(parameters: String): List<Track> {
        return musicRepository.getPlaylistTracks(parameters)
    }
}