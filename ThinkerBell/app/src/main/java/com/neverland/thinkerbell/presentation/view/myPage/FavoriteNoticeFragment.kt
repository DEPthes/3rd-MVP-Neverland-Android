package com.neverland.thinkerbell.presentation.view.myPage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.core.utils.LoggerUtil
import com.neverland.thinkerbell.databinding.FragmentFavoriteNoticeBinding
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.model.notice.NoticeItem
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.custom.CustomDividerDecoration
import com.neverland.thinkerbell.presentation.utils.CustomDividerItemDecoration
import com.neverland.thinkerbell.presentation.utils.UiState
import com.neverland.thinkerbell.presentation.view.OnRvItemClickListener
import com.neverland.thinkerbell.presentation.view.myPage.adapter.FavoriteNoticeAdapter

class FavoriteNoticeFragment(
    private val list : List<NoticeItem>,
    private val noticeType: NoticeType
) : BaseFragment<FragmentFavoriteNoticeBinding>(R.layout.fragment_favorite_notice) {

    private val favoriteNoticeViewModel : FavoriteNoticeViewModel by viewModels()
    private val noticeAdapter by lazy { FavoriteNoticeAdapter(noticeType).apply {
        setRvItemClickListener(object : OnRvItemClickListener<String> {
            override fun onClick(item: String) {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(item)
                }
                startActivity(intent)
            }
        })
        setBookmarkClickListener(object : OnRvItemClickListener<Pair<Int, Boolean>> {
            override fun onClick(item: Pair<Int, Boolean>) {
                if (item.second) {
                    favoriteNoticeViewModel.postBookmark(item.first, noticeType)
                } else {
                    favoriteNoticeViewModel.deleteBookmark(item.first, noticeType)
                }
            }
        })
    } }

    override fun initView() {
    }

    override fun setObserver() {
        super.setObserver()
        favoriteNoticeViewModel.bookmarkState.observe(viewLifecycleOwner) {
            when(it) {
                is UiState.Loading -> {

                }
                is UiState.Error -> {
                    showToast("북마크 수정 실패")
                }
                is UiState.Success -> {
                    showToast("북마크 수정 성공")
                }
                is UiState.Empty -> {

                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNoticeList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noticeAdapter
            addItemDecoration(CustomDividerDecoration(requireContext(), "#898989", 0.8f))
        }
        noticeAdapter.submitList(list.toMutableList())
    }

}