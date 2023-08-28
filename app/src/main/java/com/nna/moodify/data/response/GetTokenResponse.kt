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