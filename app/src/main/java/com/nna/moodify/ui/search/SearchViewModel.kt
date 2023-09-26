package com.nna.moodify.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nna.moodify.domain.music.GetCategoriesUseCase
import com.nna.moodify.domain.successOr
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {
    private val _categories = flow {
        emit(getCategoriesUseCase(30).successOr(emptyList()))
    }

    val categories = _categories.shareIn(viewModelScope, SharingStarted.WhileSubscribed(), 1)
}
