package com.neverland.thinkerbell.presentation.view.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neverland.thinkerbell.core.utils.LoggerUtil
import com.neverland.thinkerbell.data.repository.DataStoreRepositoryImpl
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.model.notice.NoticeItem
import com.neverland.thinkerbell.domain.repository.DataStoreRepository
import com.neverland.thinkerbell.domain.usecase.notice.SearchAllNoticesByCategoryUseCase
import com.neverland.thinkerbell.presentation.base.ThinkerBellApplication.Companion.application
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val dataStoreRepository: DataStoreRepository = DataStoreRepositoryImpl()
    private val searchAllNoticesByCategoryUseCase = SearchAllNoticesByCategoryUseCase()

    private val _recentSearchWords = MutableLiveData<UiState<List<String>>>()
    val recentSearchWords: LiveData<UiState<List<String>>> get() = _recentSearchWords

    private val _uiState = MutableLiveData<UiState<Pair<String, Map<NoticeType, List<NoticeItem>>>>>(UiState.Loading)
    val uiState : LiveData<UiState<Pair<String, Map<NoticeType, List<NoticeItem>>>>> get() = _uiState

    init {
        fetchRecentSearchWords()
    }

    private fun fetchRecentSearchWords() {
        _recentSearchWords.value = UiState.Loading
        viewModelScope.launch {
            dataStoreRepository.readRecentSearchWords().collect { words ->
                if (words.isEmpty()) {
                    _recentSearchWords.postValue(UiState.Empty)
                } else {
                    _recentSearchWords.postValue(UiState.Success(words))
                }
            }
        }
    }

    fun addSearchWord(searchWord: String) {
        viewModelScope.launch {
            dataStoreRepository.saveRecentSearchWord(searchWord)
            fetchRecentSearchWords()
        }
    }

    fun deleteSearchWord(searchWord: String) {
        viewModelScope.launch {
            dataStoreRepository.deleteRecentSearchWord(searchWord)
            fetchRecentSearchWords()
        }
    }

    fun searchAllNotices(keyword: String){
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            searchAllNoticesByCategoryUseCase.invoke(keyword = keyword, ssaId = application.getAndroidId())
                .onSuccess {
                    _uiState.value = if(it.isEmpty()) UiState.Empty else UiState.Success(Pair(keyword, it))
                }
                .onFailure {
                    _uiState.value = UiState.Error(Exception(keyword))
                }
        }
    }

    fun setUiStateLoading(){
        _uiState.value = UiState.Loading
    }



    private val _sortState = MutableLiveData<UiState<List<NoticeType>>>(UiState.Loading)
    val sortState: LiveData<UiState<List<NoticeType>>> get() = _sortState

    fun fetchData(originalList: List<NoticeType>) {
        _sortState.value = UiState.Loading
        viewModelScope.launch {
            try {
                dataStoreRepository.readCategoryOrder().collect { list ->
                    val sortedNoticeTypes = list.ifEmpty { NoticeType.entries }.toMutableList()
                    sortedNoticeTypes.remove(NoticeType.ACADEMIC_SCHEDULE)

                    val orderMap = sortedNoticeTypes.mapIndexed { index, noticeType ->
                        noticeType.enName to index
                    }.toMap()

                    val sortedOriginalList = originalList.sortedBy {
                        orderMap[it.enName] ?: Int.MAX_VALUE
                    }

                    _sortState.value = UiState.Success(sortedOriginalList)
                }
            } catch (e : Exception){
                _sortState.value = UiState.Error(e)
            }
        }
    }
}
