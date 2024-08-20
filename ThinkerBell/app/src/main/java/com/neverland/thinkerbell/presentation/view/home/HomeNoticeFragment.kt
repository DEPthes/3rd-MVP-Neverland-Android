package com.neverland.thinkerbell.presentation.view.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentHomeNoticeBinding
import com.neverland.thinkerbell.domain.model.notice.NoticeItem
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.custom.CustomDividerDecoration
import com.neverland.thinkerbell.presentation.view.home.adapter.HomeNoticeAdapter


class HomeNoticeFragment(
    private val notices: List<NoticeItem.CommonNotice>
) : BaseFragment<FragmentHomeNoticeBinding>(R.layout.fragment_home_notice) {

    override fun onResume() {
        super.onResume()

        binding.root.requestLayout()
    }

    override fun initView() {
        binding.rvHomeNotice.layoutManager = LinearLayoutManager(context)
        binding.rvHomeNotice.adapter = HomeNoticeAdapter(notices)

        val customDividerItemDecoration = CustomDividerDecoration(requireContext(), "#898989", 0.8f)
        binding.rvHomeNotice.addItemDecoration(customDividerItemDecoration)
    }
}