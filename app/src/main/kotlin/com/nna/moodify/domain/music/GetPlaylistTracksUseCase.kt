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

package com.nna.moodify.domain.music

import com.nna.moodify.di.IoDispatcher
import com.nna.moodify.domain.UseCase
import com.nna.moodify.domain.model.PlaylistDetail
import com.nna.moodify.domain.model.Track
import com.nna.moodify.domain.repositories.MusicRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetPlaylistTracksUseCase @Inject constructor(
    private val musicRepository: MusicRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): UseCase<String, PlaylistDetail>(dispatcher) {
    override suspend fun execute(parameters: String): PlaylistDetail {
        return musicRepository.getPlaylistDetail(parameters)
    }
}
