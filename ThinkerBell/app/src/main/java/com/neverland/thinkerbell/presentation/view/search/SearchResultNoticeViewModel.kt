package com.neverland.thinkerbell.presentation.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neverland.thinkerbell.core.utils.LoggerUtil
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.usecase.bookmark.DeleteBookmarkUseCase
import com.neverland.thinkerbell.domain.usecase.bookmark.PostBookmarkUseCase
import com.neverland.thinkerbell.presentation.base.ThinkerBellApplication
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.launch

class SearchResultNoticeViewModel: ViewModel() {

    private val _toastState = MutableLiveData<UiState<String>>(UiState.Loading)
    val toastState: LiveData<UiState<String>> get() = _toastState

    fun postBookmark(category: NoticeType, noticeId: Int){
        viewModelScope.launch {
            PostBookmarkUseCase().invoke(
                ssaId = ThinkerBellApplication.application.getAndroidId(),
                category = category,
                noticeId = noticeId
            )
                .onFailure {
                    LoggerUtil.e("[${category.koName}] 북마크 실패: ${it.message}")
                    _toastState.value = UiState.Success("북마크 실패")
                }
                .onSuccess {
                    LoggerUtil.d("[${category.koName}] 북마크 성공")
                    _toastState.value = UiState.Success("북마크 성공")
                }

        }
    }

    fun deleteBookmark(category: NoticeType, noticeId: Int){
        viewModelScope.launch {
            DeleteBookmarkUseCase().invoke(
                ssaId = ThinkerBellApplication.application.getAndroidId(),
                category = category,
                noticeId = noticeId
            )
                .onFailure {
                    LoggerUtil.e("[${category.koName}] 북마크 삭제 실패: ${it.message}")
                    _toastState.value = UiState.Success("북마크 삭제 실패")
                }
                .onSuccess {
                    LoggerUtil.d("[${category.koName}] 북마크 삭제 성공")
                    _toastState.value = UiState.Success("북마크 삭제 성공")
                }
        }
    }
}