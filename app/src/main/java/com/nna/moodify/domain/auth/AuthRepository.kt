package com.nna.moodify.domain.auth

import com.nna.moodify.domain.model.BearerToken

interface AuthRepository {
    fun getValidToken(): BearerToken
}
