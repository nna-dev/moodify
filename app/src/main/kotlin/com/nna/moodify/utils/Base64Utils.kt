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
