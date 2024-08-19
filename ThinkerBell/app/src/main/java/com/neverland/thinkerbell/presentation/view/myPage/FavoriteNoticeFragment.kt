package com.neverland.thinkerbell.presentation.view.myPage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentFavoriteNoticeBinding
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.model.notice.NoticeItem
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.view.myPage.adapter.FavoriteNoticeAdapter

class FavoriteNoticeFragment(
    private val list : List<NoticeItem>,
    private val noticeType: NoticeType
) : BaseFragment<FragmentFavoriteNoticeBinding>(R.layout.fragment_favorite_notice) {

    private val noticeAdapter by lazy { FavoriteNoticeAdapter(noticeType) }

    override fun initView() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNoticeList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noticeAdapter
        }
        noticeAdapter.submitList(list.toMutableList())
    }

}