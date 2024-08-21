package com.neverland.thinkerbell.presentation.view.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neverland.thinkerbell.core.utils.LoggerUtil
import com.neverland.thinkerbell.data.repository.DataStoreRepositoryImpl
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.launch

class CategoryViewModel: ViewModel() {
    private val dataStoreRepositoryImpl = DataStoreRepositoryImpl()


    private val _uiState = MutableLiveData<UiState<List<NoticeType>>>(UiState.Loading)
    val uiState: LiveData<UiState<List<NoticeType>>> get() = _uiState


    fun fetchData() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                dataStoreRepositoryImpl.readCategoryOrder().collect { list ->
                    val result = list.ifEmpty { NoticeType.entries }.toMutableList()
                    result.remove(NoticeType.ACADEMIC_SCHEDULE)
                    _uiState.value = UiState.Success(result)
                }
            } catch (e : Exception){
                _uiState.value = UiState.Error(e)
            }
        }
    }

    fun saveCategoryOrder(list: List<NoticeType>){
        viewModelScope.launch {
            dataStoreRepositoryImpl.saveCategoryOrder(list)
                .onFailure {
                    LoggerUtil.d("카테고리 순서 실패 완료")
                }
                .onSuccess {
                    LoggerUtil.d("카테고리 순서 저장 완료")
                }
        }
    }
}