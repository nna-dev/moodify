package com.nna.moodify.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nna.moodify.databinding.FragmentSearchBinding
import com.nna.moodify.ui.ViewBindingFragment
import kotlinx.coroutines.launch

class SearchFragment : ViewBindingFragment<FragmentSearchBinding>() {
    private val viewModel: SearchViewModel by viewModels()
    private val adapter = SearchCategoriesAdapter()

    override fun onBind(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchRecycler.apply {
            adapter = this@SearchFragment.adapter
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