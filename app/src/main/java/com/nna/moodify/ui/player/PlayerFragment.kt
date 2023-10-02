package com.nna.moodify.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.nna.moodify.databinding.FragmentPlayerBinding
import com.nna.moodify.player.MusicStreamable
import com.nna.moodify.ui.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerFragment : ViewBindingFragment<FragmentPlayerBinding>() {
    private val viewModel: PlayerViewModel by viewModels()

    override fun onBind(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentPlayerBinding = FragmentPlayerBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = navArgs<PlayerFragmentArgs>()
        viewModel.playStreamable(MusicStreamable.of(args.value.mediaUrl, "Title"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.musicPlayer.stop()
    }
}