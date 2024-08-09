package com.neverland.thinkerbell.presentation.view.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentHomeNoticeBinding
import com.neverland.thinkerbell.domain.model.Notice
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.utils.CustomDividerItemDecoration
import com.neverland.thinkerbell.presentation.view.home.adapter.HomeNoticeAdapter


class HomeNoticeFragment(
    private val notices: List<Notice>
) : BaseFragment<FragmentHomeNoticeBinding>(R.layout.fragment_home_notice) {

    override fun initView() {
        binding.rvHomeNotice.layoutManager = LinearLayoutManager(context)
        binding.rvHomeNotice.adapter = HomeNoticeAdapter(notices)

        val customDividerItemDecoration = CustomDividerItemDecoration(requireContext())
        binding.rvHomeNotice.addItemDecoration(customDividerItemDecoration)
    }
}