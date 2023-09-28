package com.nna.moodify.ui.browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nna.moodify.R
import com.nna.moodify.databinding.FragmentBrowseBinding
import com.nna.moodify.extensions.addGridItemSpacing
import com.nna.moodify.ui.ViewBindingFragment
import com.nna.moodify.utils.SpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BrowseFragment : ViewBindingFragment<FragmentBrowseBinding>() {
    private val viewModel: BrowseViewModel by viewModels()
    private val adapter = BrowseCategoriesAdapter()

    override fun onBind(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentBrowseBinding {
        return FragmentBrowseBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchRecycler.apply {
            adapter = this@BrowseFragment.adapter
            layoutManager = GridLayoutManager(requireContext(), 3, RecyclerView.HORIZONTAL, false)
            val space = resources.getDimensionPixelSize(R.dimen.margin)
            addItemDecoration(SpacingItemDecoration(space, 0, 0))
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.categories.collect {
                        adapter.submitList(it)
                    }
                }
            }
        }
    }
}