package com.neverland.thinkerbell.presentation.view.search.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.neverland.thinkerbell.presentation.view.search.SearchResultNoticeFragment

class SearchResultVPAdapter(
    fragment: Fragment,
    private val categories: List<String>,
    private val searchWord: String,
    private val updateTotalNoticesCount: (Int) -> Unit
) : FragmentStateAdapter(fragment) {

    private val fragments = categories.map { SearchResultNoticeFragment.newInstance(it, searchWord) }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

    private fun getTotalNoticesCount(): Int {
        return fragments.sumBy { it.getNoticesCount() }
    }

    init {
        updateTotalNoticesCount(getTotalNoticesCount())
    }
}
