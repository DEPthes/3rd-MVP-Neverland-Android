package com.neverland.thinkerbell.presentation.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neverland.thinkerbell.domain.model.univ.Banner
import com.neverland.thinkerbell.domain.usecase.univ.GetBannerUseCase
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val getBannerUseCase = GetBannerUseCase()

    private val _banners = MutableLiveData<UiState<List<Banner>>>()
    val banners: LiveData<UiState<List<Banner>>> get() = _banners

    fun fetchBanners() {
        _banners.value = UiState.Loading
        viewModelScope.launch {
            getBannerUseCase.invoke()
            .onSuccess { banner ->
                _banners.value = UiState.Success(banner)
            }.onFailure { exception ->
                _banners.value = UiState.Error(exception)
            }
        }
    }
}