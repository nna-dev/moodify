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
