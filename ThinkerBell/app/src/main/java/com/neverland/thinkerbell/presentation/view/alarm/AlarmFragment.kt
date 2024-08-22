package com.neverland.thinkerbell.presentation.view.alarm

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.google.android.material.tabs.TabLayoutMediator
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.core.utils.LoggerUtil
import com.neverland.thinkerbell.databinding.FragmentAlarmBinding
import com.neverland.thinkerbell.domain.model.keyword.Keyword
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.utils.UiState
import com.neverland.thinkerbell.presentation.view.home.HomeActivity
import com.neverland.thinkerbell.presentation.view.myPage.KeywordManageFragment

class AlarmFragment : BaseFragment<FragmentAlarmBinding>(R.layout.fragment_alarm) {
    private val alarmViewModel: AlarmViewModel by viewModels()

    override fun initView() {
    }

    override fun setObserver() {
        super.setObserver()
        alarmViewModel.keywords.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    // Show loading state if needed
                }
                is UiState.Success -> {
                    state.data.forEach {
                        alarmViewModel.checkAlarm(Keyword(it.keyword))
                    }
                }
                is UiState.Error -> {
                    // Handle error state
                }

                UiState.Empty -> {
                    binding.groupEmpty.visibility = View.VISIBLE
                }
            }
        }

        alarmViewModel.checkAlarm.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Loading -> {
                    // Show loading state if needed
                }
                is UiState.Success -> {
                    alarmViewModel.addUnCheckedList(if(it.data.second) it.data.first else Keyword(""))
                    alarmViewModel.count++
                }
                is UiState.Error -> {
                    // Handle error state
                }

                UiState.Empty -> {

                }
            }
        }

        alarmViewModel.unCheckedList.observe(viewLifecycleOwner) {
            if (alarmViewModel.keywordSize <= alarmViewModel.count) {
                setupTabLayout(it.toList())
            }
        }

    }

    private fun setupTabLayout(unCheckedList: List<Keyword>) {
        val adapter = AlarmVPAdapter(this, alarmViewModel.keywordList)
        binding.vpAlarmNotice.adapter = adapter

        TabLayoutMediator(binding.tlAlarmCategoryTab, binding.vpAlarmNotice) { tab, position ->
            val tabView = layoutInflater.inflate(R.layout.tab_alarm_custum_view, null)
            val tabTitle = tabView.findViewById<TextView>(R.id.tab_title)
            val redDot = tabView.findViewById<View>(R.id.red_dot)

            tabTitle.text = alarmViewModel.keywordList[position].keyword

            // 알림이 있는 경우
            if (unCheckedList.contains(alarmViewModel.keywordList[position])) {
                redDot.visibility = View.VISIBLE
            } else {
                redDot.visibility = View.GONE
            }

            tab.customView = tabView
        }.attach()
    }

    override fun initListener() {
        super.initListener()
        binding.btnEmpty.setOnClickListener {
            (requireActivity() as HomeActivity).replaceFragment(R.id.fl_home, KeywordManageFragment(), true)
        }
    }

}