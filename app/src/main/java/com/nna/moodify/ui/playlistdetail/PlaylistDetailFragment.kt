package com.nna.moodify.ui.playlistdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.nna.moodify.databinding.FragmentPlaylistDetailBinding
import timber.log.Timber
import kotlin.math.abs

class PlaylistDetailFragment : Fragment() {
    private lateinit var viewModel: PlaylistDetailViewModel
    private var _binding: FragmentPlaylistDetailBinding? = null
    private val binding: FragmentPlaylistDetailBinding
        get() = _binding!!

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