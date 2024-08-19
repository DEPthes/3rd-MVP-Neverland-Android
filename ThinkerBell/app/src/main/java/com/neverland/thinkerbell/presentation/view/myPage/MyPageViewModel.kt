package com.neverland.thinkerbell.presentation.view.myPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neverland.thinkerbell.domain.model.notice.BookmarkNotice
import com.neverland.thinkerbell.domain.model.notice.NoticeItem
import com.neverland.thinkerbell.domain.usecase.notice.GetBookmarkNoticeUseCase
import com.neverland.thinkerbell.presentation.base.ThinkerBellApplication.Companion.application
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.launch

class MyPageViewModel: ViewModel() {

    private val getBookmarkNoticeUseCase = GetBookmarkNoticeUseCase()
    private val _favoriteNotices = MutableLiveData<UiState<List<NoticeItem>>>(UiState.Loading)
    val favoriteNotices: LiveData<UiState<List<NoticeItem>>> get() = _favoriteNotices

    init{
        fetchFavoriteNotices(application.getAndroidId())
    }
    fun fetchFavoriteNotices(ssaId: String) {
        viewModelScope.launch {
            getBookmarkNoticeUseCase.invoke(ssaId)
                .onSuccess { bookmarkNotice ->
                    //_favoriteNotices.value = UiState.Success(bookmarkNotice)
                }
                .onFailure { exception ->
                    _favoriteNotices.value = UiState.Error(exception)
                }
        }
    }
}