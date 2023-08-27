package com.nna.moodify.domain.model

import java.time.LocalDateTime

data class BearerToken(
    val accessToken: String,
    val creationTime: LocalDateTime,
    val validTimeInSecond: Int
) {
    val value = "Bearer $accessToken"
}

fun BearerToken.isExpired(): Boolean {
    val expiredTime = creationTime.plusSeconds(validTimeInSecond.toLong())
    return LocalDateTime.now().isAfter(expiredTime)
}
