package com.neverland.thinkerbell.presentation.view.alarm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neverland.thinkerbell.core.utils.LoggerUtil
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.model.alarm.Alarm
import com.neverland.thinkerbell.domain.usecase.alarm.GetAlarmUseCase
import com.neverland.thinkerbell.domain.usecase.alarm.ReadAlarmUseCase
import com.neverland.thinkerbell.domain.usecase.bookmark.DeleteBookmarkUseCase
import com.neverland.thinkerbell.domain.usecase.bookmark.PostBookmarkUseCase
import com.neverland.thinkerbell.presentation.base.ThinkerBellApplication
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.launch

class AlarmNoticeViewModel: ViewModel() {

    private val getAlarmUseCase = GetAlarmUseCase()
    private val readAlarmUseCaes = ReadAlarmUseCase()

    private val postBookmarkNoticeUseCase = PostBookmarkUseCase()
    private val deleteBookmarkNoticeUseCase = DeleteBookmarkUseCase()

    private val _alarmNotices = MutableLiveData<UiState<List<Alarm>>>(UiState.Loading)
    val alarmNotices: LiveData<UiState<List<Alarm>>> get() = _alarmNotices

    private val _readAlarm = MutableLiveData<UiState<String>>(UiState.Loading)
    val readAlarm : LiveData<UiState<String>> get() = _readAlarm

    private val _bookmarkState = MutableLiveData<UiState<String>>(UiState.Loading)
    val bookmarkState: LiveData<UiState<String>> get() = _bookmarkState

    fun fetchAlarmNotices(keyword: String) {
        _alarmNotices.value = UiState.Loading
        viewModelScope.launch {
            getAlarmUseCase.invoke(ssaId = ThinkerBellApplication.application.getAndroidId(), keyword = keyword)
                .onSuccess { alarmNotices ->
                    _alarmNotices.value = UiState.Success(alarmNotices)
                }
                .onFailure { exception ->
                    _alarmNotices.value = UiState.Error(exception)
                }
        }
    }

    fun readAlarmNotice(alarmId: Int) {
        _readAlarm.value = UiState.Loading
        viewModelScope.launch {
            readAlarmUseCaes.invoke(alarmId = alarmId)
                .onSuccess { readAlarm ->
                    _readAlarm.value = UiState.Success(readAlarm)
                }
                .onFailure { exception ->
                    _readAlarm.value = UiState.Error(exception)
                }
        }
    }

    fun postBookmark(category: NoticeType, noticeId: Int){
        _bookmarkState.value = UiState.Loading
        viewModelScope.launch {
            PostBookmarkUseCase().invoke(
                ssaId = ThinkerBellApplication.application.getAndroidId(),
                category = category,
                noticeId = noticeId
            )
                .onFailure {
                    LoggerUtil.e("[${category.koName}] 북마크 실패: ${it.message}")
                    _bookmarkState.value = UiState.Success("북마크 실패")
                }
                .onSuccess {
                    LoggerUtil.d("[${category.koName}] 북마크 성공")
                    _bookmarkState.value = UiState.Success("북마크 성공")
                }

        }
    }

    fun deleteBookmark(category: NoticeType, noticeId: Int){
        _bookmarkState.value = UiState.Loading
        viewModelScope.launch {
            DeleteBookmarkUseCase().invoke(
                ssaId = ThinkerBellApplication.application.getAndroidId(),
                category = category,
                noticeId = noticeId
            )
                .onFailure {
                    LoggerUtil.e("[${category.koName}] 북마크 삭제 실패: ${it.message}")
                    _bookmarkState.value = UiState.Success("북마크 삭제 실패")
                }
                .onSuccess {
                    LoggerUtil.d("[${category.koName}] 북마크 삭제 성공")
                    _bookmarkState.value = UiState.Success("북마크 삭제 성공")
                }
        }
    }

}