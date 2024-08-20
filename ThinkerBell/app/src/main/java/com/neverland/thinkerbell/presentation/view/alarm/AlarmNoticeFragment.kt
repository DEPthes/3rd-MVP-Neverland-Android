package com.neverland.thinkerbell.presentation.view.alarm

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.core.utils.LoggerUtil
import com.neverland.thinkerbell.databinding.FragmentAlarmNoticeBinding
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.model.alarm.Alarm
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.custom.CustomDividerDecoration
import com.neverland.thinkerbell.presentation.utils.UiState
import com.neverland.thinkerbell.presentation.view.OnRvItemClickListener
import com.neverland.thinkerbell.presentation.view.alarm.adapter.AlarmNoticeAdapter

class AlarmNoticeFragment : BaseFragment<FragmentAlarmNoticeBinding>(R.layout.fragment_alarm_notice) {

    private lateinit var noticeAdapter : AlarmNoticeAdapter
    private val alarmNoticeViewModel: AlarmNoticeViewModel by viewModels()
    companion object {
        private const val ARG_KEYWORD = "keyword"

        fun newInstance(keyword: String) = AlarmNoticeFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_KEYWORD, keyword)
            }
        }
    }

    private lateinit var keyword: String

    override fun initView() {
        LoggerUtil.d(arguments?.getString(ARG_KEYWORD) ?: "")
        noticeAdapter = AlarmNoticeAdapter().apply {
            setRvItemClickListener(object : OnRvItemClickListener<Pair<Int, String>>{
                override fun onClick(item: Pair<Int, String>) {
                    alarmNoticeViewModel.readAlarmNotice(item.first)
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(item.second)
                    }
                    startActivity(intent)
                }
            })
            setBookmarkClickListener(object : OnRvItemClickListener<Pair<Alarm, Boolean>>{
                override fun onClick(item: Pair<Alarm, Boolean>) {
                    val noticeType = NoticeType.entries.find{
                        it.enName.lowercase() == item.first.noticeTypeEnglish.lowercase()
                    }?: NoticeType.NORMAL_NOTICE
                    LoggerUtil.d(item.first.noticeTypeEnglish)
                    if (item.second) {
                        alarmNoticeViewModel.postBookmark(noticeType, item.first.id)
                    } else {
                        alarmNoticeViewModel.deleteBookmark(noticeType, item.first.id)
                    }
                }
            })
        }
        binding.rvNoticeList.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(CustomDividerDecoration(requireContext(), "#404040", 1f ))
            adapter = noticeAdapter
        }
        alarmNoticeViewModel.fetchAlarmNotices(arguments?.getString(ARG_KEYWORD) ?: "")
    }

    override fun setObserver() {
        super.setObserver()
        alarmNoticeViewModel.alarmNotices.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    // Show loading state if needed
                }
                is UiState.Success -> {
                    noticeAdapter.submitList(state.data)
                }
                is UiState.Error -> {
                    // Handle error state
                }
                UiState.Empty -> {

                }
            }
        }

        alarmNoticeViewModel.readAlarm.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    // Show loading state if needed
                }
                is UiState.Success -> {

                }
                is UiState.Error -> {
                    // Handle error state
                }
                UiState.Empty -> {

                }
            }
        }

        alarmNoticeViewModel.bookmarkState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {}
                is UiState.Error -> {}
                is UiState.Empty -> {}
                is UiState.Success -> {
                    showToast(state.data)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        keyword = arguments?.getString(ARG_KEYWORD) ?: ""
    }

}
