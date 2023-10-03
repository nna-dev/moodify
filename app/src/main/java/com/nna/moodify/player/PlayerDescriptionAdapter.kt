package com.nna.moodify.player

import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.nna.moodify.R

class PlayerDescriptionAdapter(
    private val context: Context,
    private val streamableInfoProvider: () -> StreamableInfo?
) : PlayerNotificationManager.MediaDescriptionAdapter {

    override fun getCurrentContentTitle(player: Player): CharSequence {
        return streamableInfoProvider()?.title ?: context.getString(R.string.common_unknown)
    }

    override fun createCurrentContentIntent(player: Player): PendingIntent? {
        return null
    }

    override fun getCurrentContentText(player: Player): CharSequence? {
        return streamableInfoProvider()?.subTitle
    }

    override fun getCurrentLargeIcon(
        player: Player,
        callback: PlayerNotificationManager.BitmapCallback
    ): Bitmap? {
        return ContextCompat.getDrawable(context, R.drawable.ic_home)?.toBitmap(64, 64)
    }
}