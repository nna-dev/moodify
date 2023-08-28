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
