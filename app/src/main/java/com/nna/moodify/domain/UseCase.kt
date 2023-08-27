package com.nna.moodify.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber

abstract class UseCase<in P, R>(private val dispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            withContext(dispatcher) {
                Result.Success(execute(parameters))
            }
        } catch (e: Exception) {
            Timber.d(e)
            Result.Error(e)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R
}
