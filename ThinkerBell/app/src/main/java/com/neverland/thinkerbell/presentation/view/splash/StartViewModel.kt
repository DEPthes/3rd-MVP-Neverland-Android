package com.neverland.thinkerbell.presentation.view.splash

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neverland.thinkerbell.core.utils.LoggerUtil
import com.neverland.thinkerbell.domain.usecase.user.PostUserInfoUseCase
import com.neverland.thinkerbell.presentation.base.ThinkerBellApplication.Companion.application
import com.neverland.thinkerbell.presentation.fcm.MyFirebaseMessagingService
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.launch

class StartViewModel: ViewModel() {
    private val postUserInfoUseCase = PostUserInfoUseCase()

    private val _fcmState = MutableLiveData<UiState<Unit>>(UiState.Loading)
    val fcmState: LiveData<UiState<Unit>> get() = _fcmState

    @SuppressLint("HardwareIds")
    fun saveDeviceInfo(){
        _fcmState.value = UiState.Loading

        try {
            MyFirebaseMessagingService().getRegistrationToken { token ->
                if (token != null) {
                    viewModelScope.launch {
                        postUserInfoUseCase(application.getAndroidId() ?: "", token)
                            .onSuccess {
                                _fcmState.value = UiState.Success(Unit)
                            }
                            .onFailure { e ->
                                _fcmState.value = UiState.Error(e)
                                LoggerUtil.e("Register fcm failed: ${e.message}")
                            }
                    }
                } else {
                    throw Exception("Null Fcm Token Exception")
                }
            }
        } catch (e: Exception) {
            _fcmState.value = UiState.Error(e)
            LoggerUtil.e("Register fcm exception: ${e.message}")
        }
    }

}