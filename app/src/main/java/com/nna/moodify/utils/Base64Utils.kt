package com.nna.moodify.utils

import android.util.Base64

fun String.toBase64(): String {
    return this.toByteArray(Charsets.UTF_8).toBase64()
}

fun ByteArray.toBase64(): String {
    return Base64.encodeToString(this, Base64.NO_WRAP or Base64.NO_PADDING)
}

fun String.fromBase64(): String {
    return Base64.decode(this.toByteArray(Charsets.UTF_8), Base64.NO_WRAP or Base64.NO_PADDING)
        .toString(Charsets.UTF_8)
}
