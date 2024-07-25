package com.neverland.thinkerbell.presentation.view.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neverland.thinkerbell.domain.usecase.GetExampleDataUseCase
import com.neverland.thinkerbell.domain.usecase.SaveExampleDataUseCase
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExampleViewModel: ViewModel() {
    private val saveExampleDataUseCase: SaveExampleDataUseCase = SaveExampleDataUseCase()
    private val getExampleDataUseCase: GetExampleDataUseCase = GetExampleDataUseCase()

    private val _exampleState = MutableStateFlow<UiState<String>>(UiState.Loading)
    val exampleState: StateFlow<UiState<String>> = _exampleState

    init {
        fetchExampleData()
    }

    private fun fetchExampleData() {
        viewModelScope.launch {
            _exampleState.value = UiState.Loading

            try {
                getExampleDataUseCase().collect { data ->
                    if (data != null) {
                        _exampleState.value = UiState.Success(data)
                    } else {
                        _exampleState.value = UiState.Empty
                    }
                }
            } catch (e: Exception) {
                _exampleState.value = UiState.Error(e)
            }
        }
    }

    fun saveData(value: String) {
        viewModelScope.launch {
            try {
                saveExampleDataUseCase(value)
                fetchExampleData() // 데이터 저장 후 다시 로드
            } catch (e: Exception) {
                _exampleState.value = UiState.Error(e)
            }
        }
    }
}
