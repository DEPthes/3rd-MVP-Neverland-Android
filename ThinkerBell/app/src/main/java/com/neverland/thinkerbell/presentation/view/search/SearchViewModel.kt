package com.neverland.thinkerbell.presentation.view.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.neverland.thinkerbell.data.repository.DataStoreRepositoryImpl
import com.neverland.thinkerbell.domain.repository.DataStoreRepository
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val dataStoreRepository: DataStoreRepository = DataStoreRepositoryImpl()

    private val _recentSearchWords = MutableLiveData<UiState<List<String>>>()
    val recentSearchWords: LiveData<UiState<List<String>>> get() = _recentSearchWords

    private val _searchResultCount = MutableLiveData<Int>()
    val searchResultCount: LiveData<Int> get() = _searchResultCount

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

    fun updateSearchResultCount(count: Int) {
        _searchResultCount.value = count
    }
}
