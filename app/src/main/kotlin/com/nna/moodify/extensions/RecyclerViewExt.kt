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

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.annotation.IntRange
import androidx.recyclerview.widget.GridLayoutManager

fun RecyclerView.addVerticalItemSpacing(
    @IntRange(from = 0) vertical: Int = 0,
    @IntRange(from = 0) horizontal: Int = 0,
    ignoreFirst: Boolean = false,
    ignoreLast: Boolean = false
) {
    addItemDecoration(object : ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.top = vertical
            outRect.bottom = vertical
            outRect.left = horizontal
            outRect.right = horizontal
            if (ignoreFirst || ignoreLast) {
                val position = parent.getChildAdapterPosition(view)
                if (ignoreFirst && position == 0) outRect.top = 0
                if (ignoreLast && position == state.itemCount - 1) outRect.bottom = 0
            }
        }
    })
}

fun RecyclerView.addGridItemSpacing(
    @IntRange(from = 0) vertical: Int = 0,
    @IntRange(from = 0) horizontal: Int = 0,
    ignoreStartEndRow: Boolean = false,
    ignoreFirstLastRow: Boolean = false
) {
    addItemDecoration(object : ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.top = vertical
            outRect.bottom = vertical
            outRect.left = horizontal
            outRect.right = horizontal
            val spanCount = (parent.layoutManager as GridLayoutManager).spanCount
            if (ignoreStartEndRow) {
                val position = parent.getChildAdapterPosition(view)
                val col = position % spanCount
                if (col == 0) outRect.left = 0
                if (col == spanCount - 1) outRect.right = 0
            }
            if (ignoreFirstLastRow) {
                val position = parent.getChildAdapterPosition(view)
                val row = position / spanCount
                if (row == 0) outRect.top = 0
                if (row == state.itemCount / spanCount) outRect.bottom = 0
            }
        }
    })
}
