package com.neverland.thinkerbell.presentation.view.sample

import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.ActivityHomeBinding
import com.neverland.thinkerbell.presentation.base.BaseActivity
import com.neverland.thinkerbell.presentation.utils.UiState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ExampleActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    private val viewModel: ExampleViewModel by viewModels()

    override fun initView() {

    }

    override fun initListener() {
        super.initListener()
    }

    override fun setObserver() {
        super.setObserver()

        // Flow 사용 시 예시
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.exampleState.collectLatest {
                    when (it) {
                        is UiState.Loading -> {}
                        is UiState.Success -> {

                        }
                        is UiState.Empty -> {
                            showToast("Empty Data")
                        }
                        is UiState.Error -> {
                            showToast(it.exception.message.toString())
                        }
                    }
                }
            }
        }

        // 그냥 LiveData 사용 시 예시
//        viewModel.exampleState.observe(this) { uiState ->
//            when (uiState) {
//                is UiState.Loading -> {
//                    // 로딩 중
//                }
//                is UiState.Success -> {
//                    // 데이터를 UI에 반영
//                    val data = uiState.data
//                    // 예: textView.text = data
//                }
//                is UiState.Error -> {
//                    // 오류 처리
//                    val exception = uiState.exception
//                    // 예: showError(exception)
//                }
//                is UiState.Empty -> {
//                    // 데이터 없음
//                    // 예: showEmptyState()
//                }
//            }
//        })
    }
}