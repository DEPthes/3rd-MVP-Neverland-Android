package com.neverland.thinkerbell.presentation.view.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.neverland.thinkerbell.domain.model.notice.RecentNotices
import com.neverland.thinkerbell.presentation.view.home.HomeNoticeFragment

class HomeCategoryVPAdapter (
    private val fragmentActivity: FragmentActivity,
    private val notices: RecentNotices
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {

        val list = when(position){
            0 -> notices.normalNotices
            1 -> notices.eventNotices
            2 -> notices.academicNotices
            3 -> notices.scholarshipNotices
            4 -> notices.careerNotices
            else -> emptyList()
        }

        return HomeNoticeFragment(list)
    }
}