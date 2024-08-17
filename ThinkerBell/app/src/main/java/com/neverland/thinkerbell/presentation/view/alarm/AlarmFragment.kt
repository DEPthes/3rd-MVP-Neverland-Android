package com.neverland.thinkerbell.presentation.view.alarm

import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentAlarmBinding
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.utils.UiState

class AlarmFragment : BaseFragment<FragmentAlarmBinding>(R.layout.fragment_alarm) {
    private val alarmViewModel: AlarmViewModel by viewModels()

    override fun initView() {
        alarmViewModel.keywords.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    // Show loading state if needed
                }
                is UiState.Success -> {
                    setupTabLayout(state.data)
                }
                is UiState.Error -> {
                    // Handle error state
                }

                UiState.Empty -> TODO()
            }
        }
    }

    private fun setupTabLayout(keywords: List<String>) {
        val adapter = AlarmVPAdapter(this, keywords)
        binding.vpAlarmNotice.adapter = adapter

        TabLayoutMediator(binding.tlAlarmCategoryTab, binding.vpAlarmNotice) { tab, position ->
            val tabView = layoutInflater.inflate(R.layout.tab_alarm_custum_view, null)
            val tabTitle = tabView.findViewById<TextView>(R.id.tab_title)
            val redDot = tabView.findViewById<View>(R.id.red_dot)

            tabTitle.text = keywords[position]

            // 알림이 있는 경우
            if (hasNotificationForKeyword(keywords[position])) {
                redDot.visibility = View.VISIBLE
            } else {
                redDot.visibility = View.GONE
            }

            tab.customView = tabView
        }.attach()
    }

    // 특정 키워드에 대한 알림이 있는지 확인하는 로직
    private fun hasNotificationForKeyword(keyword: String): Boolean {
        // 예를 들어, 알림 데이터를 가지고 특정 키워드에 알림이 있는지 확인
        return true /* 키워드에 해당하는 알림이 있으면 true 반환 */
    }

}