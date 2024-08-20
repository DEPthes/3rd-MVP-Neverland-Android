package com.neverland.thinkerbell.presentation.view.alarm

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.neverland.thinkerbell.domain.model.keyword.Keyword

class AlarmVPAdapter(fragment: Fragment, private val keywords: List<Keyword>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = keywords.size

    override fun createFragment(position: Int): Fragment {
        return AlarmNoticeFragment.newInstance(keywords[position].keyword)
    }
}
