package com.neverland.thinkerbell.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.neverland.thinkerbell.domain.model.Notice
import com.neverland.thinkerbell.presentation.view.HomeNoticeFragment

class HomeCategoryVPAdapter (
    fragmentActivity: FragmentActivity,
    private val notices: List<List<Notice>>
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return notices.size
    }

    override fun createFragment(position: Int): Fragment {
        return HomeNoticeFragment.newInstance(notices[position])
    }
}