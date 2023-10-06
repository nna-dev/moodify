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

package com.nna.moodify.player

import android.content.Context
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.google.android.exoplayer2.util.NotificationUtil
import com.nna.moodify.R
import dagger.hilt.android.qualifiers.ApplicationContext
import timber.log.Timber
import javax.inject.Inject

class ExoMusicPlayer @Inject constructor(
    @ApplicationContext private val context: Context,
    private val exoPlayer: ExoPlayer
) : MusicPlayer, Player.Listener {

    private var currentStreamable: Streamable? = null

    private val notificationManager by lazy {
        PlayerNotificationManager.Builder(context, NOTIFICATION_ID, NOTIFICATION_CHANNEL_ID)
            .setChannelImportance(NotificationUtil.IMPORTANCE_LOW)
            .setChannelNameResourceId(R.string.playback_notification_channel_name)
            .setMediaDescriptionAdapter(PlayerDescriptionAdapter(context) { currentStreamable?.info })
            .build()
    }

    init {
        exoPlayer.addListener(this@ExoMusicPlayer)
        notificationManager.setPlayer(exoPlayer)
    }

    override fun play(streamable: Streamable) {
        with(exoPlayer) {
            if (streamable != currentStreamable) {
                pause()
                currentStreamable = streamable
                setMediaItem(MediaItem.fromUri(streamable.info.url))
                prepare()
                play()
            }
        }
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun stop() {
        with(exoPlayer) {
            stop()
            release()
            notificationManager.setPlayer(null)
        }
    }

    override fun resume() {
        TODO("Not yet implemented")
    }

    override fun onEvents(player: Player, events: Player.Events) {
        super.onEvents(player, events)
        Timber.d("onEvents $events")
    }

    companion object {
        private const val NOTIFICATION_CHANNEL_ID = "com.nna.moodify.player.ExoMusicPlayer"
        private const val NOTIFICATION_ID = 1
    }
}
