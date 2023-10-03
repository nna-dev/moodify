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