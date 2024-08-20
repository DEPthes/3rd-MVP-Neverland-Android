package com.neverland.thinkerbell.presentation.view.myPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.usecase.bookmark.DeleteBookmarkUseCase
import com.neverland.thinkerbell.domain.usecase.bookmark.PostBookmarkUseCase
import com.neverland.thinkerbell.presentation.base.ThinkerBellApplication.Companion.application
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.launch

class FavoriteNoticeViewModel : ViewModel() {

    private val postBookmarkNoticeUseCase = PostBookmarkUseCase()

    private val deleteBookmarkNoticeUseCase = DeleteBookmarkUseCase()

    private val _bookmarkState = MutableLiveData<UiState<Unit>>(UiState.Loading)
    val bookmarkState: LiveData<UiState<Unit>> get() = _bookmarkState

    fun postBookmark(noticeId: Int, noticeType: NoticeType) {
        _bookmarkState.value = UiState.Loading

        viewModelScope.launch {
            postBookmarkNoticeUseCase.invoke(noticeId = noticeId, category = noticeType, ssaId = application.getAndroidId())
                .onSuccess { bookmarkNotice ->
                    _bookmarkState.value = UiState.Success(Unit)
                }
                .onFailure { exception ->
                    _bookmarkState.value = UiState.Error(exception)
                }
        }
    }

    fun deleteBookmark(noticeId: Int, noticeType: NoticeType) {
        _bookmarkState.value = UiState.Loading

        viewModelScope.launch {
            deleteBookmarkNoticeUseCase.invoke(noticeId = noticeId, category = noticeType, ssaId = application.getAndroidId())
                .onSuccess { bookmarkNotice ->
                    _bookmarkState.value = UiState.Success(Unit)
                }
                .onFailure { exception ->
                    _bookmarkState.value = UiState.Error(exception)
                }
        }
    }

}