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
    @ApplicationContext context: Context,
    private val exoPlayer: ExoPlayer
) : MusicPlayer, Player.Listener {
    private var currentStreamable: Streamable? = null
    private val notificationManager = PlayerNotificationManager.Builder(
        context,
        NOTIFICATION_ID,
        NOTIFICATION_CHANNEL_ID,
    ).setChannelImportance(NotificationUtil.IMPORTANCE_LOW)
        .setChannelNameResourceId(R.string.notification_channel_name)
        .setChannelDescriptionResourceId(R.string.notification_channel_description)
        .setMediaDescriptionAdapter(PlayerDescriptionAdapter()).build()

    override fun play(streamable: Streamable) {
        with(exoPlayer) {
            addListener(this@ExoMusicPlayer)
            if (streamable.info.url == null) return@with
            if (streamable == currentStreamable) return@with
            if (isPlaying) stop()
            setMediaItem(MediaItem.fromUri(streamable.info.url!!))
            prepare()
            currentStreamable = streamable
            notificationManager.setPlayer(exoPlayer)
            play()
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
        private const val NOTIFICATION_CHANNEL_ID = "com.nna.moodify.NOTIFICATION_CHANNEL_ID"
        private const val NOTIFICATION_ID = 1
    }
}