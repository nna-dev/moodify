package com.nna.moodify.data.auth

import com.nna.moodify.domain.model.BearerToken

interface AuthDataSource {
    fun saveToken(token: BearerToken)
    fun getToken(): BearerToken?
}