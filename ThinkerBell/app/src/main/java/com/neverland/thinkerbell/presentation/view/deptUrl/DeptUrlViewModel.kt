package com.neverland.thinkerbell.presentation.view.deptUrl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neverland.thinkerbell.domain.model.group.Group
import com.neverland.thinkerbell.domain.model.univ.DeptUrl
import com.neverland.thinkerbell.domain.usecase.univ.GetDeptUrlUseCase
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.launch

class DeptUrlViewModel: ViewModel() {
    private val getDeptUrlUseCase = GetDeptUrlUseCase()

    private val _uiState = MutableLiveData<UiState<List<Group<DeptUrl>>>>(UiState.Loading)
    val uiState: LiveData<UiState<List<Group<DeptUrl>>>> get() = _uiState


    fun fetchData() {
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            getDeptUrlUseCase.invoke()
                .onSuccess {
                    val groups = mutableListOf<Group<DeptUrl>>()
                    it.forEach { (key, value) ->
                        groups.add(Group(name = key, subGroups = emptyList(), items = value))
                    }

                    _uiState.value = UiState.Success(groups)
                }
                .onFailure {
                    _uiState.value = UiState.Error(it)
                }
        }
    }
}