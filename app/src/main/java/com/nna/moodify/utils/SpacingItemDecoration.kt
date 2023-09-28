package com.nna.moodify.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration constructor(
    private val space: Int,
    private val verticalEdge: Int,
    private val horizontalEdge: Int,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        when (val layoutManager = parent.layoutManager) {
            is GridLayoutManager -> {
                val isReversed = layoutManager.reverseLayout

                // In almost case orientation of grid layout manager is not used
                createGridLinearSpace(
                    outRect,
                    position,
                    state.itemCount,
                    layoutManager.spanCount,
                    layoutManager.orientation,
                    isReversed
                )

            }
            is LinearLayoutManager -> {
                val isReversed = layoutManager.reverseLayout xor layoutManager.stackFromEnd
                createLinearSpace(
                    outRect,
                    position,
                    state.itemCount,
                    layoutManager.orientation,
                    isReversed
                )
            }
        }
    }

    private fun createLinearSpace(
        outRect: Rect,
        position: Int,
        itemCount: Int,
        @RecyclerView.Orientation orientation: Int,
        isReversed: Boolean
    ) {
        val isFirstPosition = position == 0
        val isLastPosition = position == itemCount - 1

        when (orientation) {
            RecyclerView.VERTICAL -> {
                val topSpace = if (isFirstPosition) verticalEdge else space
                val bottomSpace = if (isLastPosition) verticalEdge else NO_SPACE
                outRect.apply {
                    top = if (isReversed) bottomSpace else topSpace
                    left = horizontalEdge
                    right = horizontalEdge
                    bottom = if (isReversed) topSpace else bottomSpace
                }
            }
            RecyclerView.HORIZONTAL -> {
                val leftSpace = if (isFirstPosition) horizontalEdge else space
                val rightSpace = if (isLastPosition) horizontalEdge else NO_SPACE
                outRect.apply {
                    top = verticalEdge
                    left = if (isReversed) leftSpace else rightSpace
                    right = if (isReversed) rightSpace else leftSpace
                    bottom = verticalEdge
                }
            }
        }
    }

    private fun createGridLinearSpace(
        outRect: Rect,
        position: Int,
        itemCount: Int,
        spanCount: Int,
        @RecyclerView.Orientation orientation: Int,
        isReversed: Boolean,
    ) {
        val lastRow = itemCount / spanCount
        val lastCol = spanCount - 1

        val originRow = position / spanCount
        val originCol = position % spanCount

        var row = 0
        var col = 0

        when (orientation) {
            RecyclerView.VERTICAL -> {
                if (isReversed) {
                    row = lastRow - originRow
                    col = originCol
                } else {
                    row = position / spanCount
                    col = position % spanCount
                }
            }
            RecyclerView.HORIZONTAL -> {
                // TODO Handle horizontal
//                if (isReversed) {
//                    row = originCol
//                    col = lastRow - originRow
//                } else {
//                    row = originCol
//                    col = originRow
//                }
            }
        }

        outRect.apply {
            top = if (row == 0) verticalEdge else space
            bottom = if (row == lastRow) verticalEdge else NO_SPACE
            left = if (col == 0) horizontalEdge else space
            right = if (col == lastCol) horizontalEdge else NO_SPACE
        }
    }

    companion object {
        private const val NO_SPACE = 0
    }
}
