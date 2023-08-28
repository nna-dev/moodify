package com.nna.moodify.data.auth

import com.nna.moodify.BuildConfig
import com.nna.moodify.data.response.GetTokenResponse.Companion.toBearerToken
import com.nna.moodify.domain.model.BearerToken
import com.nna.moodify.domain.repositories.AuthRepository
import com.nna.moodify.domain.errors.AuthenticationError
import com.nna.moodify.domain.model.isExpired
import com.nna.moodify.utils.toBase64
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val prefDataSource: AuthDataSource
) : AuthRepository {
    private var cachedToken: BearerToken? = null

    private fun getSpotifyBasicToken(): String {
        val rawKey = "${BuildConfig.SPOTIFY_CLIENT_ID}:${BuildConfig.SPOTIFY_CLIENT_SECRET}"
        return "Basic ${rawKey.toBase64()}"
    }

    override fun getValidToken(): BearerToken {
        if (cachedToken == null) cachedToken = prefDataSource.getToken()
        cachedToken?.let { token ->
            if (token.isExpired()) {
                cachedToken = null
            } else {
                return token
            }
        }

        val response = authService.getNewToken(getSpotifyBasicToken()).execute()
        if (response.isSuccessful) {
            response.body()?.toBearerToken()?.let {
                prefDataSource.saveToken(it)
                cachedToken = it
                return it
            }
        }
        throw AuthenticationError()
    }
}