package com.neverland.thinkerbell.presentation.view

import androidx.activity.viewModels
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.ActivityMainBinding
import com.neverland.thinkerbell.presentation.base.BaseActivity
import com.neverland.thinkerbell.presentation.view.sample.ExampleViewModel

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