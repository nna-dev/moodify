package com.nna.moodify.ui.epoxyhelpers

import android.content.Context
import android.util.AttributeSet
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView

@ModelView(saveViewState = true, autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class CarouselNoSnap : Carousel {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        isNestedScrollingEnabled = false
    }
    override fun getSnapHelperFactory(): SnapHelperFactory? = null
}