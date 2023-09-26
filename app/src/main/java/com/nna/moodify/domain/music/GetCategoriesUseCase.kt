package com.nna.moodify.domain.music

import com.nna.moodify.di.IoDispatcher
import com.nna.moodify.domain.UseCase
import com.nna.moodify.domain.model.Category
import com.nna.moodify.domain.repositories.MusicRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val musicRepository: MusicRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : UseCase<Int, List<Category>>(coroutineDispatcher) {
    override suspend fun execute(parameters: Int): List<Category> {
        return musicRepository.getCategories(parameters)
    }
}