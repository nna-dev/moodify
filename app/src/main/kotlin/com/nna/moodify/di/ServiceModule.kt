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

package com.nna.moodify.di

import com.nna.moodify.BuildConfig
import com.nna.moodify.data.SpotifyUrls
import com.nna.moodify.data.auth.AuthService
import com.nna.moodify.data.client.AuthorizationHeaderInterceptor
import com.nna.moodify.data.music.MusicService
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {

    @Provides
    @Singleton
    fun provideMoshiConverter(): MoshiConverterFactory = MoshiConverterFactory.create(
        Moshi.Builder()
            .build()
    )

    @Provides
    @Singleton
    @NonAuthenticatedOkHttpClient
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    @Provides
    @Singleton
    @AuthenticatedOkHttpClient
    fun provideAuthenticatedClient(authorizationHeaderInterceptor: AuthorizationHeaderInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            builder.addInterceptor(loggingInterceptor)
        }
        builder.addInterceptor(authorizationHeaderInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideAuthService(
        @NonAuthenticatedOkHttpClient client: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): AuthService =
        Retrofit.Builder()
            .client(client)
            .baseUrl(SpotifyUrls.AUTH_BASE_URL)
            .addConverterFactory(moshiConverterFactory)
            .build()
            .create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideMusicService(
        @AuthenticatedOkHttpClient client: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): MusicService =
        Retrofit.Builder()
            .client(client)
            .baseUrl(SpotifyUrls.BASE_URL)
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .addConverterFactory(moshiConverterFactory)
            .build()
            .create(MusicService::class.java)
}
