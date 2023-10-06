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

package com.nna.moodify.data.response

import com.nna.moodify.domain.errors.AuthenticationError
import com.nna.moodify.domain.model.BearerToken
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
data class GetTokenResponse(
    @Json(name = "access_token") val accessToken: String,
    @Json(name = "token_type") val tokenType: String,
    @Json(name = "expires_in") val validTimeInSecond: Int
) {
    companion object {
        private const val BEARER_TOKEN_TYPE = "Bearer"

        @JvmStatic
        fun GetTokenResponse.toBearerToken(): BearerToken {
            if (tokenType != BEARER_TOKEN_TYPE) throw AuthenticationError()
            return BearerToken(
                accessToken = accessToken,
                creationTime = LocalDateTime.now(),
                validTimeInSecond = validTimeInSecond
            )
        }
    }
}
