package com.nna.moodify.data.client

import com.nna.moodify.domain.auth.AuthRepository
import com.nna.moodify.domain.errors.AuthenticationError
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import timber.log.Timber

class AuthorizationHeaderInterceptor(
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
//            if (e is AuthenticationError) {
//                Timber.d(e)
//            }
//            return Response.Builder()
//                .code(600)
//                .protocol(Protocol.HTTP_2)
//                .body(null)
//                .request(chain.request())
//                .build()
        }
    }
}