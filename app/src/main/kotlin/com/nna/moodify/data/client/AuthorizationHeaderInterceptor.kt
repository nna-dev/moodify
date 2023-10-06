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

package com.nna.moodify.data.client

import com.nna.moodify.domain.repositories.AuthRepository
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

class AuthorizationHeaderInterceptor @Inject constructor(
    private val authRepository: AuthRepository
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return try {
            val token = authRepository.getValidToken()
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", token.value)
                .build()
            chain.proceed(newRequest)
        } catch (e: Exception) {
            Timber.e(e)

            // TODO: process or return unauthorized
            chain.proceed(chain.request())
        }
    }
}
