package com.neverland.thinkerbell.presentation.view.alarm

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentAlarmNoticeBinding
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.view.alarm.adapter.AlarmNoticeAdapter

class AlarmNoticeFragment : BaseFragment<FragmentAlarmNoticeBinding>(R.layout.fragment_alarm_notice) {

    private val noticeAdapter = AlarmNoticeAdapter()
    private val viewModel: AlarmViewModel by viewModels()
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

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        keyword = arguments?.getString(ARG_KEYWORD) ?: ""
        binding.rvNoticeList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noticeAdapter
        }

        // Observe the notices and update the adapter
        viewModel.notices.observe(viewLifecycleOwner) { notices ->
            val filteredNotices = notices[keyword] ?: emptyList()
            noticeAdapter.submitList(filteredNotices)
        }
    }

}
