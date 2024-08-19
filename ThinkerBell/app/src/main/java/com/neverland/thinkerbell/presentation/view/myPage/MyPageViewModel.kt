package com.neverland.thinkerbell.presentation.view.myPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neverland.thinkerbell.domain.model.notice.RecentBookmarkNotice
import com.neverland.thinkerbell.domain.usecase.bookmark.GetRecentBookmarkNoticeUseCase
import com.neverland.thinkerbell.presentation.base.ThinkerBellApplication.Companion.application
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.launch

class MyPageViewModel: ViewModel() {

    private val getRecentBookmarkNoticeUseCase = GetRecentBookmarkNoticeUseCase()
    private val _recentFavoriteNotices = MutableLiveData<UiState<List<RecentBookmarkNotice>>>(UiState.Loading)
    val recentFavoriteNotices: LiveData<UiState<List<RecentBookmarkNotice>>> get() = _recentFavoriteNotices

    init{
        fetchFavoriteNotices(application.getAndroidId())
    }

    private fun fetchFavoriteNotices(ssaId: String) {
        viewModelScope.launch {
            getRecentBookmarkNoticeUseCase.invoke(ssaId)
                .onSuccess { bookmarkNotice ->
                    _recentFavoriteNotices.value = UiState.Success(bookmarkNotice)
                }
                .onFailure { exception ->
                    _recentFavoriteNotices.value = UiState.Error(exception)
                }
        }
    }
}