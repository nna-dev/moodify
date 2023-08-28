package com.nna.moodify.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.commit
import com.nna.moodify.R
import com.nna.moodify.databinding.ActivityMainBinding
import com.nna.moodify.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        addFragmentIfNeed()
    }

    private fun addFragmentIfNeed() {
        if (supportFragmentManager.findFragmentByTag("MAIN") == null) {
            supportFragmentManager.commit {
                replace(R.id.main_view, HomeFragment.newInstance(), "MAIN")
            }
        }
    }
}