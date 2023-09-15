package com.nna.moodify.ui.home

import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.TypedEpoxyController
import com.nna.moodify.ItemHomeCarouselTitleBindingModel_
import com.nna.moodify.ItemHomeLargeCarouselBindingModel_
import com.nna.moodify.ui.epoxyhelpers.CarouselNoSnapModel_

class HomeRecyclerController(
    private val carouselPadding: Carousel.Padding,
    private val onItemClickListener: OnItemClickListener
) : TypedEpoxyController<List<HomeCarousel>>() {

    interface OnItemClickListener {
        fun onClickCard(item: HomeLargeCard)
    }

    @AutoModel
    lateinit var loading: ItemLoading

    override fun buildModels(data: List<HomeCarousel>) {
        if (data.isEmpty()) {
            add(loading)
            return
        }
        for (category in data) {
            add(createTitleItem(category.title))
            add(createNoSnapCarousel(category))
        }
    }

    private fun createTitleItem(title: String) =
        ItemHomeCarouselTitleBindingModel_().id(title).title(title)

    private fun createLargeCardItem(card: HomeLargeCard) =
        ItemHomeLargeCarouselBindingModel_().id(card.id)
            .item(card)
            .onItemClickListener(onItemClickListener)

    private fun createNoSnapCarousel(carousel: HomeCarousel) =
        CarouselNoSnapModel_().id(carousel.id)
            .models(carousel.items.map { createLargeCardItem(it) })
            .padding(carouselPadding)
}
