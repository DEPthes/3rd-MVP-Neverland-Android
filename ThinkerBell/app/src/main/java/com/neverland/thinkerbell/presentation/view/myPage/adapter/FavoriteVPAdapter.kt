package com.neverland.thinkerbell.presentation.view.myPage.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.model.notice.BookmarkNotice
import com.neverland.thinkerbell.domain.model.notice.NoticeItem
import com.neverland.thinkerbell.presentation.view.myPage.FavoriteNoticeFragment

class FavoriteVPAdapter(fragment: Fragment, private val category: Map<NoticeType, List<NoticeItem>>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = category.size

    override fun createFragment(position: Int): Fragment {
        val key = category.keys.toList()[position]
        return FavoriteNoticeFragment(category[key] ?: emptyList(), NoticeType.entries.find { it.enName==key.enName }!!)
    }

}