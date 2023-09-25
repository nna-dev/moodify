package com.nna.moodify.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn

class SearchViewModel : ViewModel() {
    private val _categories = flow<List<String>> {
        emit((1..10).map { "Hello" })
    }
    val categories = _categories.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), 1)
}
