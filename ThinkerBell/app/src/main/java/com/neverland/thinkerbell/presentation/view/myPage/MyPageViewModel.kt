package com.neverland.thinkerbell.presentation.view.myPage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neverland.thinkerbell.domain.model.keyword.Keyword
import com.neverland.thinkerbell.domain.model.notice.RecentBookmarkNotice
import com.neverland.thinkerbell.domain.usecase.bookmark.GetRecentBookmarkNoticeUseCase
import com.neverland.thinkerbell.domain.usecase.keyword.DeleteKeywordUseCase
import com.neverland.thinkerbell.domain.usecase.keyword.GetKeywordUseCase
import com.neverland.thinkerbell.domain.usecase.keyword.PostKeywordUseCase
import com.neverland.thinkerbell.presentation.base.ThinkerBellApplication.Companion.application
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.launch

class MyPageViewModel: ViewModel() {

    private val getRecentBookmarkNoticeUseCase = GetRecentBookmarkNoticeUseCase()
    private val getKeywordUseCase = GetKeywordUseCase()
    private val postKeywordUseCase = PostKeywordUseCase()
    private val deleteKeywordUseCase = DeleteKeywordUseCase()

    private val _recentFavoriteNotices = MutableLiveData<UiState<List<RecentBookmarkNotice>>>(UiState.Loading)
    val recentFavoriteNotices: LiveData<UiState<List<RecentBookmarkNotice>>> get() = _recentFavoriteNotices

    private val _keyword = MutableLiveData<UiState<List<Keyword>>>(UiState.Loading)
    val keyword: LiveData<UiState<List<Keyword>>> get() = _keyword

    private val _postState = MutableLiveData<UiState<Unit>>(UiState.Loading)
    val postState: LiveData<UiState<Unit>> get() = _postState

    private val _deleteState = MutableLiveData<UiState<Unit>>(UiState.Loading)
    val deleteState: LiveData<UiState<Unit>> get() = _deleteState

    init{
        fetchFavoriteNotices()
        fetchKeyword()
    }

    fun fetchFavoriteNotices() {
        _recentFavoriteNotices.value = UiState.Loading
        viewModelScope.launch {
            getRecentBookmarkNoticeUseCase.invoke(application.getAndroidId())
                .onSuccess { bookmarkNotice ->
                    _recentFavoriteNotices.value = UiState.Success(bookmarkNotice)
                }
                .onFailure { exception ->
                    _recentFavoriteNotices.value = UiState.Error(exception)
                }
        }
    }

    fun fetchKeyword() {
        _keyword.value = UiState.Loading
        viewModelScope.launch {
            getKeywordUseCase.invoke(application.getAndroidId())
                .onSuccess { keyword ->
                    _keyword.value = UiState.Success(keyword)
                }
                .onFailure { exception ->
                    _keyword.value = UiState.Error(exception)
                }
        }
    }

    fun postKeyword(keyword: String) {
        _postState.value = UiState.Loading
        viewModelScope.launch {
            postKeywordUseCase.invoke(ssaId = application.getAndroidId(), keyword = keyword)
                .onSuccess { _postState.value = UiState.Success(Unit) }
                .onFailure { _postState.value = UiState.Error(it) }
        }
    }

    fun deleteKeyword(keyword: String) {
        _deleteState.value = UiState.Loading
        viewModelScope.launch {
            deleteKeywordUseCase.invoke(ssaId = application.getAndroidId(), keyword = keyword)
                .onSuccess { _deleteState.value = UiState.Success(Unit) }
                .onFailure { _deleteState.value = UiState.Error(it) }
        }
    }
}