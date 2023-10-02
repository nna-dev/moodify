package com.nna.moodify.ui.playlistdetail

import android.graphics.Color
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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nna.moodify.R
import com.nna.moodify.databinding.FragmentPlaylistDetailBinding
import com.nna.moodify.domain.model.getDefaultImageUri
import com.nna.moodify.extensions.addVerticalItemSpacing
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import java.text.NumberFormat
import kotlin.math.abs

@AndroidEntryPoint
class PlaylistDetailFragment : Fragment() {
    private val viewModel: PlaylistDetailViewModel by viewModels()
    private var _binding: FragmentPlaylistDetailBinding? = null
    private val binding: FragmentPlaylistDetailBinding
        get() = _binding!!

    private val tracksAdapter: TracksAdapter by lazy { TracksAdapter(this::onTrackItemClick) }

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

            binding.toolbarLayout.isTitleEnabled = scrollPercentage < TOOLBAR_TITLE_ENABLE_THRESHOLD
        }

        binding.recyclerTracks.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = tracksAdapter
            addVerticalItemSpacing(vertical = resources.getDimensionPixelSize(R.dimen.margin))
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.detailState.collect { state ->
                        when (state) {
                            is PlaylistDetailState.Success -> {
                                val detail = state.playlistDetail
                                tracksAdapter.submitList(detail.tracks.map {
                                    TrackViewModel.fromTrack(
                                        it
                                    )
                                })
                                detail.playlist.primaryColor?.let {
                                    Timber.d("primary color $it")
                                    binding.toolbarLayout.setContentScrimColor(Color.parseColor(it))
                                }
                                detail.playlist.images.getDefaultImageUri()?.let {
                                    Glide.with(requireContext())
                                        .load(it)
                                        .centerInside()
                                        .into(binding.image)
                                }
                                binding.toolbarLayout.title = detail.playlist.name
                                binding.playlistDescription.text = detail.playlist.description
                                binding.tvFollowers.text =
                                    NumberFormat.getNumberInstance().format(detail.followerCount)
                            }
                            else -> {
                                // TODO
                            }
                        }
                    }
                }
            }
        }
    }

    private fun onTrackItemClick(trackViewModel: TrackViewModel) {
        viewModel.findTrackWithId(trackViewModel.trackId)?.previewUrl?.let { previewUrl ->
            val action = PlaylistDetailFragmentDirections.actionPlaylistDetailToPlayerDetail(previewUrl)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val IMAGE_SCALE_THRESHOLD = 0.9f
        private const val IMAGE_OPACITY_THRESHOLD = 0.3f
        private const val TOOLBAR_TITLE_ENABLE_THRESHOLD = 0.05f
        private const val IMAGE_OPACITY_RANGE = IMAGE_SCALE_THRESHOLD - IMAGE_OPACITY_THRESHOLD
    }
}