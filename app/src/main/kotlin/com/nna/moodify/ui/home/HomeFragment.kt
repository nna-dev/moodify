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

package com.nna.moodify.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.Carousel
import com.nna.moodify.R
import com.nna.moodify.databinding.FragmentHomeBinding
import com.nna.moodify.extensions.showShortToast
import com.nna.moodify.ui.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home),
    HomeRecyclerController.OnItemClickListener {
    private val viewModel: HomeViewModel by viewModels()
    private val controller: HomeRecyclerController by lazy {
        HomeRecyclerController(
            Carousel.Padding(
                resources.getDimensionPixelSize(R.dimen.margin_2x),
                resources.getDimensionPixelSize(R.dimen.margin_2x)
            ),
            onItemClickListener = this@HomeFragment
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

    override fun onClickCard(item: HomeLargeCard) {
        showShortToast(item.title)
        val action = HomeFragmentDirections.actionNavigationHomeToPlaylistDetail(item.id)
        findNavController().navigate(action)
    }
}
