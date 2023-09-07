package com.nna.moodify.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class LinearSpacesItemDecoration private constructor(
    private var verticalSpace: Int = 0,
    private var horizontalSpace: Int = 0,
    private val isIgnoreFirstLastSpace: Boolean
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.right = horizontalSpace
        outRect.bottom = verticalSpace

        if (isIgnoreFirstLastSpace) {
            if (parent.getChildAdapterPosition(view) == state.itemCount - 1) {
                outRect.right = 0
                outRect.bottom = 0
            }
        } else {
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = verticalSpace
                outRect.left = horizontalSpace
            }
        }

    }

    companion object {
        @JvmStatic
        fun ofVertical(
            space: Int,
            isIgnoreFirstLastSpace: Boolean = true
        ): LinearSpacesItemDecoration {
            return LinearSpacesItemDecoration(space, 0, isIgnoreFirstLastSpace)
        }

        @JvmStatic
        fun ofHorizontal(
            space: Int,
            isIgnoreFirstLastSpace: Boolean = true
        ): LinearSpacesItemDecoration {
            return LinearSpacesItemDecoration(0, space, isIgnoreFirstLastSpace)
        }
    }
}