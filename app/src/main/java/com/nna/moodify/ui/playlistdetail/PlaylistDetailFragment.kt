package com.nna.moodify.ui.playlistdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.nna.moodify.R
import com.nna.moodify.databinding.FragmentPlaylistDetailBinding
import com.nna.moodify.extensions.addVerticalItemSpacing
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.math.abs

@AndroidEntryPoint
class PlaylistDetailFragment : Fragment() {
    private val viewModel: PlaylistDetailViewModel by viewModels()
    private var _binding: FragmentPlaylistDetailBinding? = null
    private val binding: FragmentPlaylistDetailBinding
        get() = _binding!!

    private val tracksAdapter: TracksAdapter by lazy { TracksAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaylistDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.appbar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val scrollPercentage =
                (1 - abs(verticalOffset).toFloat() / appBarLayout.totalScrollRange)

            val opacity =
                if (scrollPercentage >= IMAGE_SCALE_THRESHOLD) 1f
                else ((scrollPercentage - IMAGE_OPACITY_THRESHOLD) / IMAGE_OPACITY_RANGE)
                    .coerceAtLeast(0f)
            if (scrollPercentage >= IMAGE_OPACITY_THRESHOLD) {
                binding.image.apply {
                    isVisible = true
                    scaleX = scrollPercentage
                    scaleY = scrollPercentage
                    alpha = opacity
                }
            } else {
                binding.image.isVisible = false
            }
        }

        binding.recyclerTracks.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = tracksAdapter
            addVerticalItemSpacing(vertical = resources.getDimensionPixelSize(R.dimen.margin))
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.tracks.collect { tracks ->
                        tracksAdapter.submitList(tracks.map { TrackViewModel.fromTrack(it) })
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val IMAGE_SCALE_THRESHOLD = 0.9f
        private const val IMAGE_OPACITY_THRESHOLD = 0.3f
        private const val IMAGE_OPACITY_RANGE = IMAGE_SCALE_THRESHOLD - IMAGE_OPACITY_THRESHOLD
    }
}