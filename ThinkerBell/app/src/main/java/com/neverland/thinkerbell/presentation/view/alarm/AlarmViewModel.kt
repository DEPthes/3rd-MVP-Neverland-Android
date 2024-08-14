package com.neverland.thinkerbell.presentation.view.alarm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.neverland.thinkerbell.domain.model.notice.CommonNotice
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.launch

class AlarmViewModel(application: Application) : AndroidViewModel(application) {

    private val _keywords = MutableLiveData<UiState<List<String>>>()
    val keywords: LiveData<UiState<List<String>>> get() = _keywords

    private val _notices = MutableLiveData<Map<String, List<CommonNotice>>>()
    val notices: LiveData<Map<String, List<CommonNotice>>> get() = _notices

    init {
        fetchKeywords()
        fetchNotices()
    }

    private fun fetchKeywords() {
        viewModelScope.launch {
            _keywords.value = UiState.Loading
            val dummyKeywords = listOf("키워드", "입사신청", "장학금", "공모전")
            _keywords.value = UiState.Success(dummyKeywords)
        }
    }

    private fun fetchNotices() {
        viewModelScope.launch {
            val dummyNotices = mapOf(
                "키워드" to listOf(
                    CommonNotice("키워드 공지사항 1", "2024-01-01", "url1", false, "전체"),
                    CommonNotice("키워드 공지사항 2", "2024-01-02", "url2", true, "전체"),
                    CommonNotice("키워드 공지사항 3", "2024-01-03", "url3", false, "전체")
                ),
                "입사신청" to listOf(
                    CommonNotice("입사신청 공지사항 1", "2024-01-04", "url4", false, "공지"),
                    CommonNotice("입사신청 공지사항 2", "2024-01-05", "url5", true, "공지"),
                    CommonNotice("입사신청 공지사항 3", "2024-01-06", "url6", false, "공지")
                ),
                "장학금" to listOf(
                    CommonNotice("장학금 공지사항 1", "2024-01-07", "url7", false, "이벤트"),
                    CommonNotice("장학금 공지사항 2", "2024-01-08", "url8", true, "이벤트"),
                    CommonNotice("장학금 공지사항 3", "2024-01-09", "url9", false, "이벤트")
                ),
                "공모전" to listOf(
                    CommonNotice("공모전 공지사항 1", "2024-01-10", "url10", false, "업데이트"),
                    CommonNotice("공모전 공지사항 2", "2024-01-11", "url11", true, "업데이트"),
                    CommonNotice("공모전 공지사항 3", "2024-01-12", "url12", false, "업데이트")
                )
            )
            _notices.value = dummyNotices
        }
    }
}
