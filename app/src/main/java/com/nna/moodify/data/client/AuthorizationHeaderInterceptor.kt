package com.nna.moodify.data.client

import com.nna.moodify.domain.repositories.AuthRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationHeaderInterceptor @Inject constructor(
    private val authRepository: AuthRepository
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val token = authRepository.getValidToken()
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", token.value)
                .build()
            return chain.proceed(newRequest)
        } catch (e: Exception) {
            throw e
        }
    }
}