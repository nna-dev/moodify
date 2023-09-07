package com.nna.moodify.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.Carousel
import com.nna.moodify.R
import com.nna.moodify.databinding.FragmentHomeBinding
import com.nna.moodify.ui.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()
    private val controller: HomeRecyclerController by lazy {
        HomeRecyclerController(
            Carousel.Padding(
                resources.getDimensionPixelSize(R.dimen.margin_2x),
                resources.getDimensionPixelSize(R.dimen.margin_2x)
            )
        )
    }
    private val carousels = mutableListOf<HomeCarousel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.recyclerMusic.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            setItemSpacingPx(0)
            setController(controller)
            controller.setData(emptyList())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.carouselState.collect { items ->
                        carousels.clear()
                        carousels.addAll(items)
                        refreshCarouselsView()
                    }
                }
            }
        }
    }

    private fun refreshCarouselsView() {
        controller.setData(carousels.toList())
    }
}
