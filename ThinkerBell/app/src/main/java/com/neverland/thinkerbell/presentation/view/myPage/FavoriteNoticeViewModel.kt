package com.neverland.thinkerbell.presentation.view.myPage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.model.notice.BookmarkNotice
import com.neverland.thinkerbell.domain.model.notice.CommonNotice
import com.neverland.thinkerbell.domain.model.notice.NoticeItem
import com.neverland.thinkerbell.domain.usecase.notice.GetBookmarkNoticeUseCase
import com.neverland.thinkerbell.presentation.base.ThinkerBellApplication.Companion.application
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.launch

class FavoriteNoticeViewModel : ViewModel() {

    private val getBookmarkNoticeUseCase = GetBookmarkNoticeUseCase()

    private val _notices = MutableLiveData<UiState<Map<NoticeType, List<NoticeItem>>>>(UiState.Loading)
    val notices: LiveData<UiState<Map<NoticeType, List<NoticeItem>>>> get() = _notices

    init {
        fetchNotices(application.getAndroidId())
    }

    private fun fetchNotices(ssaId: String) {
        viewModelScope.launch {
            getBookmarkNoticeUseCase.invoke(ssaId)
                .onSuccess { bookmarkNotice ->
                    _notices.value = UiState.Success(bookmarkNotice)
                }
                .onFailure { exception ->
                    _notices.value = UiState.Error(exception)
                }
            //_notices.value = dummyNotices
        }
    }
}