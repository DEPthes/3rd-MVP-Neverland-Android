package com.neverland.thinkerbell.presentation.view.myPage.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.neverland.thinkerbell.presentation.view.myPage.FavoriteNoticeFragment

class FavoriteVPAdapter(fragment: Fragment, private val category: List<String>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = category.size

    override fun createFragment(position: Int): Fragment {
        return FavoriteNoticeFragment.newInstance(category[position])
    }
}