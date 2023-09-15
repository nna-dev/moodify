package com.nna.moodify.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.BaseTransientBottomBar.Duration
import timber.log.Timber

fun Fragment.showShortToast(message: String) {
    showToast(message, Toast.LENGTH_SHORT)
}

fun Fragment.showLongToast(message: String) {
    showToast(message, Toast.LENGTH_LONG)
}

private fun Fragment.showToast(message: String, @Duration duration: Int) {
    activity?.let {
        Toast.makeText(it, message, duration).show()
    } ?: kotlin.run { Timber.w("Cannot get activity") }
}