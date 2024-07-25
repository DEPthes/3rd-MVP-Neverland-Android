package com.neverland.thinkerbell.presentation.view

import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.ActivityMainBinding
import com.neverland.thinkerbell.presentation.base.BaseActivity
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel: ExampleViewModel by viewModels()

    override fun initView() {
    }

    override fun initListener() {
        super.initListener()
    }

    override fun setObserver() {
        super.setObserver()
    }
}