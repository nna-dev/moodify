package com.nna.moodify.data.client

import com.nna.moodify.domain.repositories.AuthRepository
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

class AuthorizationHeaderInterceptor @Inject constructor(
    private val authRepository: AuthRepository
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            val token = authRepository.getValidToken()
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", token.value)
                .build()
            chain.proceed(newRequest)
        } catch (e: Exception) {
            Timber.e(e)

            // TODO: process or return unauthorized
            chain.proceed(chain.request())
        }
    }
}