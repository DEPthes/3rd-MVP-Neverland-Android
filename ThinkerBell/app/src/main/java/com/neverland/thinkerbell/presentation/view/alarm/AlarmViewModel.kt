package com.neverland.thinkerbell.presentation.view.alarm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neverland.thinkerbell.domain.model.keyword.Keyword
import com.neverland.thinkerbell.domain.usecase.alarm.CheckAlarmUseCase
import com.neverland.thinkerbell.domain.usecase.keyword.GetKeywordUseCase
import com.neverland.thinkerbell.presentation.base.ThinkerBellApplication.Companion.application
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.launch

class AlarmViewModel : ViewModel() {

    private val getKeywordUseCase = GetKeywordUseCase()
    private val checkAlarmUseCase = CheckAlarmUseCase()

    var keywordSize = 0
    var keywordList = listOf<Keyword>()
    var count = 0

    private val _unCheckedList = MutableLiveData(mutableListOf<Keyword>())
    val unCheckedList: LiveData<MutableList<Keyword>> get() = _unCheckedList

    private val _keywords = MutableLiveData<UiState<List<Keyword>>>(UiState.Loading)
    val keywords: LiveData<UiState<List<Keyword>>> get() = _keywords

    private val _checkAlarm = MutableLiveData<UiState<Pair<Keyword, Boolean>>>(UiState.Loading)
    val checkAlarm: LiveData<UiState<Pair<Keyword, Boolean>>> get() = _checkAlarm

    init {
        fetchKeywords()
    }

    private fun fetchKeywords() {
        _keywords.value = UiState.Loading
        viewModelScope.launch {
            getKeywordUseCase.invoke(ssaId = application.getAndroidId())
                .onSuccess {
                    if (it.isEmpty()) {
                        _keywords.value = UiState.Empty
                    } else {
                        _keywords.value = UiState.Success(it)
                        keywordList = it
                        keywordSize = it.size
                    }
                }
                .onFailure { exception ->
                    _keywords.value = UiState.Error(exception)
                }
        }
    }

    fun checkAlarm(keyword: Keyword) {
        _checkAlarm.value = UiState.Loading
        viewModelScope.launch {
            checkAlarmUseCase.invoke(ssaId = application.getAndroidId(), keyword = keyword.keyword)
                .onSuccess {
                    _checkAlarm.value = UiState.Success(Pair(keyword, it))
                }
                .onFailure {
                    _checkAlarm.value = UiState.Error(it)
                }
        }
    }

    fun addUnCheckedList(keyword: Keyword) {
        if (keyword.keyword.isEmpty()) {
            _unCheckedList.postValue(unCheckedList.value)
        } else {
            val temp = unCheckedList.value!!
            temp.add(keyword)
            _unCheckedList.postValue(temp)
        }
    }

}
