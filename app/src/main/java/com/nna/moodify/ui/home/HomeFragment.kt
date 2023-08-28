package com.nna.moodify.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.nna.moodify.R
import com.nna.moodify.databinding.FragmentHomeBinding
import com.nna.moodify.ui.BindingFragment

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}