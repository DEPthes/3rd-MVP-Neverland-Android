package com.neverland.thinkerbell.presentation.view.splash

import android.content.Intent
import androidx.activity.viewModels
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.ActivityStartBinding
import com.neverland.thinkerbell.presentation.base.BaseActivity
import com.neverland.thinkerbell.presentation.utils.UiState
import com.neverland.thinkerbell.presentation.view.home.HomeActivity

class StartActivity: BaseActivity<ActivityStartBinding>(R.layout.activity_start) {
    private val viewModel: StartViewModel by viewModels()

    override fun initView() {
        viewModel.saveDeviceInfo()
        setStatusBarColor(R.color.primary2, false)
    }

    override fun setObserver() {
        super.setObserver()

        viewModel.fcmState.observe(this){
            when(it){
                is UiState.Loading -> {}
                is UiState.Error -> {}
                is UiState.Empty -> {}
                is UiState.Success -> {
                    moveHome()
                }
            }
        }
    }

    private fun moveHome(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}