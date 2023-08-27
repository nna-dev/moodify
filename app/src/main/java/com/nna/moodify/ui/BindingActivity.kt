package com.nna.moodify.ui

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BindingActivity<T: ViewDataBinding> constructor(
    @LayoutRes private val contentLayoutId: Int
): AppCompatActivity() {

    protected val binding: T by lazy(LazyThreadSafetyMode.NONE) {
        DataBindingUtil.setContentView(this, contentLayoutId)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
}