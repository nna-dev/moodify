package com.nna.moodify.data.auth

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import com.nna.moodify.domain.model.BearerToken
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject

class AuthSharedPrefDataSource @Inject constructor(
    @ApplicationContext private val applicationContext: Context
) : AuthDataSource {
    companion object {
        private const val PREFS_NAME = "Authentication"
        private const val MAIN_KEY_ALIAS = "SharedPrefsMainKey"
        private const val TOKEN_KEY = "AuthSharedPrefDataSource_Token"
        private const val TOKEN_CREATED_TIME_KEY = "AuthSharedPrefDataSource_CreatedTime"
        private const val TOKEN_DURATION_KEY = "AuthSharedPrefDataSource_Duration"
    }

    private val sharedPrefs = EncryptedSharedPreferences.create(
        PREFS_NAME,
        MAIN_KEY_ALIAS,
        applicationContext,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun saveToken(token: BearerToken) {
        with(sharedPrefs.edit()) {
            putString(TOKEN_KEY, token.accessToken)
            putLong(TOKEN_CREATED_TIME_KEY, token.creationTime.toEpochSecond(ZoneOffset.UTC))
            putInt(TOKEN_DURATION_KEY, token.validTimeInSecond)
            apply()
        }
    }

    override fun getToken(): BearerToken? {
        with(sharedPrefs) {
            val tokenKey = getString(TOKEN_KEY, null) ?: return null

            val epochSecond = getLong(TOKEN_CREATED_TIME_KEY, 0)
            val createdTime = if (epochSecond != 0L) LocalDateTime.ofEpochSecond(
                epochSecond,
                0,
                ZoneOffset.UTC
            ) ?: return null
            else return null

            val duration = getInt(TOKEN_DURATION_KEY, 0)
            if (duration == 0) return null

            return BearerToken(tokenKey, createdTime, duration)
        }
    }
}
