package com.nna.moodify.domain.repositories

import com.nna.moodify.domain.model.BearerToken

interface AuthRepository {
    fun getValidToken(): BearerToken
}
