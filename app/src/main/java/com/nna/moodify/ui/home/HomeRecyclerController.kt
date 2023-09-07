package com.nna.moodify.ui.home

import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.TypedEpoxyController
import com.nna.moodify.ItemHomeCarouselTitleBindingModel_
import com.nna.moodify.ItemHomeLargeCarouselBindingModel_
import com.nna.moodify.ui.epoxyhelpers.CarouselNoSnapModel_

class HomeRecyclerController(
    private val carouselPadding: Carousel.Padding
) : TypedEpoxyController<List<HomeCarousel>>() {

    @AutoModel
    lateinit var loading: ItemLoading

    override fun buildModels(data: List<HomeCarousel>) {
        if (data.isEmpty()) {
            add(loading)
            return
        }
        for (category in data) {
            val items = category.items.map { item ->
                ItemHomeLargeCarouselBindingModel_().id(item.id).item(item)
            }
            add(ItemHomeCarouselTitleBindingModel_().id(category.title).title(category.title))
            add(
                CarouselNoSnapModel_()
                    .id(category.id)
                    .models(items)
                    .padding(carouselPadding)
            )
        }
    }
}