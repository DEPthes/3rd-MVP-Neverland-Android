package com.neverland.thinkerbell.presentation.view.search.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.model.notice.NoticeItem
import com.neverland.thinkerbell.presentation.view.search.SearchResultNoticeFragment

class SearchResultVPAdapter(
    fragment: Fragment,
    private val categories: List<NoticeType>,
    private val allNotices: Map<NoticeType, List<NoticeItem>>,
) : FragmentStateAdapter(fragment) {

    private val fragments = categories.map { SearchResultNoticeFragment(it, allNotices[it] ?: emptyList()) }

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]
}
