package com.neverland.thinkerbell.presentation.view.myPage

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentMyPageBinding
import com.neverland.thinkerbell.domain.model.notice.CommonNotice
import com.neverland.thinkerbell.domain.model.notice.NoticeItem
import com.neverland.thinkerbell.domain.model.notice.RecentBookmarkNotice
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.utils.UiState
import com.neverland.thinkerbell.presentation.view.home.HomeActivity
import com.neverland.thinkerbell.presentation.view.myPage.adapter.FavoriteNoticeAdapter
import com.neverland.thinkerbell.presentation.view.myPage.adapter.MyPageFavoriteNoticeAdapter
import com.neverland.thinkerbell.presentation.view.myPage.adapter.MyPageKeywordAdapter

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val myPageviewModel: MyPageViewModel by viewModels()
    private lateinit var myPageFavoriteNoticeAdapter: MyPageFavoriteNoticeAdapter
    private lateinit var keywordAdapter: MyPageKeywordAdapter

    override fun initView() {

    }

    override fun setObserver() {
        super.setObserver()
        myPageviewModel.recentFavoriteNotices.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Loading -> {
                    // Handle loading state
                }

                is UiState.Success -> {
                    setupFavoriteNoticesRecyclerView(it.data)
                    setupKeywordRecyclerView()
                }

                is UiState.Error -> {
                    // Handle error state
                }

                UiState.Empty -> {

                }
            }
        }
    }

    private fun setupKeywordRecyclerView() {
        val keywords = listOf("키워드1", "키워드2", "키워드3", "키워드4", "키워드5") // 예시 데이터

        keywordAdapter = MyPageKeywordAdapter(keywords)
        binding.rvMyPageKeyword.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = keywordAdapter
        }
    }

    private fun setupFavoriteNoticesRecyclerView(list: List<RecentBookmarkNotice>) {
        myPageFavoriteNoticeAdapter =
            MyPageFavoriteNoticeAdapter(if (list.size >= 3) list.subList(0, 3) else list)
        binding.rvMyPageFavorite.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = myPageFavoriteNoticeAdapter
        }
    }

    override fun initListener() {
        super.initListener()
        binding.ibPageRightFavorite.setOnClickListener {
            (requireActivity() as HomeActivity).replaceFragment(
                R.id.fl_home,
                FavoriteFragment(),
                true
            )
        }
    }

}