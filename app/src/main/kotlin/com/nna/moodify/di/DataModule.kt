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

import com.nna.moodify.data.auth.AuthDataSource
import com.nna.moodify.data.auth.AuthRepositoryImpl
import com.nna.moodify.data.auth.AuthSharedPrefDataSource
import com.nna.moodify.data.music.DemoMusicDataSource
import com.nna.moodify.data.music.MusicDataSource
import com.nna.moodify.data.music.MusicRepositoryImpl
import com.nna.moodify.data.music.RemoteMusicDataSource
import com.nna.moodify.domain.repositories.AuthRepository
import com.nna.moodify.domain.repositories.MusicRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface DataModule {
    @Binds
    @Singleton
    fun bindAuthDataSource(dataSource: AuthSharedPrefDataSource): AuthDataSource

    @Binds
    @Singleton
    fun bindAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun bindMusicRepository(musicRepository: MusicRepositoryImpl): MusicRepository

    @Binds
    @Singleton
    @Named("RemoteMusicDataSource")
    fun bindRemoteMusicDataSource(dataSource: RemoteMusicDataSource): MusicDataSource

    @Binds
    @Singleton
    @Named("DemoMusicDataSource")
    fun bindDemoMusicDataSource(dataSource: DemoMusicDataSource): MusicDataSource
}
