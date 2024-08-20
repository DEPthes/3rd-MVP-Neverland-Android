package com.neverland.thinkerbell.presentation.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neverland.thinkerbell.domain.model.univ.AcademicSchedule
import com.neverland.thinkerbell.domain.usecase.univ.GetAcademicScheduleUseCase
import com.neverland.thinkerbell.presentation.base.ThinkerBellApplication.Companion.application
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.launch

class HomeCalendarViewModel: ViewModel() {
    private val getAcademicScheduleUseCase = GetAcademicScheduleUseCase()

    private val _uiState = MutableLiveData<UiState<List<AcademicSchedule>>>(UiState.Loading)
    val uiState: LiveData<UiState<List<AcademicSchedule>>> get() = _uiState


    fun fetchData(month: Int) {
        _uiState.value = UiState.Loading

        viewModelScope.launch {
            getAcademicScheduleUseCase.invoke(month, ssaId = application.getAndroidId())
                .onSuccess { schedules ->
                    _uiState.value = UiState.Success(schedules)
                }
                .onFailure { exception ->
                    _uiState.value = UiState.Error(exception)
                }
        }
    }
}