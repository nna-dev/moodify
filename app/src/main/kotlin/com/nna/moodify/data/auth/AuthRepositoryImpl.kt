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
