package com.neverland.thinkerbell.presentation.view.myPage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.neverland.thinkerbell.domain.model.notice.CommonNotice
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.launch

class FavoriteNoticeViewModel(application: Application) : AndroidViewModel(application) {
    private val _category = MutableLiveData<UiState<List<String>>>()
    val category: LiveData<UiState<List<String>>> get() = _category

    private val _notices = MutableLiveData<Map<String, List<CommonNotice>>>()
    val notices: LiveData<Map<String, List<CommonNotice>>> get() = _notices

    init {
        fetchKeywords()
        fetchNotices()
    }

    private fun fetchKeywords() {
        viewModelScope.launch {
            _category.value = UiState.Loading
            val dummyKeywords = listOf("학사", "장학/학자금", "학생활동", "생활관")
            _category.value = UiState.Success(dummyKeywords)
        }
    }

    private fun fetchNotices() {
        viewModelScope.launch {
            val dummyNotices = mapOf(
                "학사" to listOf(
                    CommonNotice("학사 공지사항 1", "2024-01-01", "url1", false, "학사"),
                    CommonNotice("학사 공지사항 2", "2024-01-02", "url2", true, "학사"),
                    CommonNotice("학사 공지사항 3", "2024-01-03", "url3", false, "학사")
                ),
                "장학/학자금" to listOf(
                    CommonNotice("장학 공지사항 1", "2024-01-04", "url4", false, "장학/학자금"),
                    CommonNotice("장학 공지사항 2", "2024-01-05", "url5", true, "장학/학자금"),
                    CommonNotice("장학 공지사항 3", "2024-01-06", "url6", false, "장학/학자금")
                ),
                "학생활동" to listOf(
                    CommonNotice("학생활동 공지사항 1", "2024-01-07", "url7", false, "학생활동"),
                    CommonNotice("학생활동 공지사항 2", "2024-01-08", "url8", true, "학생활동"),
                    CommonNotice("학생활동 공지사항 3", "2024-01-09", "url9", false, "학생활동")
                ),
                "생활관" to listOf(
                    CommonNotice("생활관 공지사항 1", "2024-01-10", "url10", false, "생활관"),
                    CommonNotice("생활관 공지사항 2", "2024-01-11", "url11", true, "생활관"),
                    CommonNotice("생활관 공지사항 3", "2024-01-12", "url12", false, "생활관")
                )
            )
            _notices.value = dummyNotices
        }
    }
}