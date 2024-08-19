package com.neverland.thinkerbell.presentation.view.myPage

import androidx.recyclerview.widget.LinearLayoutManager
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentMyPageBinding
import com.neverland.thinkerbell.domain.model.notice.CommonNotice
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.view.home.HomeActivity
import com.neverland.thinkerbell.presentation.view.myPage.adapter.FavoriteNoticeAdapter
import com.neverland.thinkerbell.presentation.view.myPage.adapter.MyPageFavoriteNoticeAdapter
import com.neverland.thinkerbell.presentation.view.myPage.adapter.MyPageKeywordAdapter

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private lateinit var favoriteNoticeAdapter: MyPageFavoriteNoticeAdapter
    private lateinit var keywordAdapter: MyPageKeywordAdapter
    override fun initView() {
        setupKeywordRecyclerView()
        setupFavoriteNoticesRecyclerView()
    }

    private fun setupKeywordRecyclerView() {
        val keywords = listOf("키워드1", "키워드2", "키워드3", "키워드4", "키워드5") // 예시 데이터

        keywordAdapter = MyPageKeywordAdapter(keywords)
        binding.rvMyPageKeyword.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = keywordAdapter
        }
    }

    private fun setupFavoriteNoticesRecyclerView() {
        val favoriteNotices = getFavoriteNotices().sortedByDescending { it.date }.take(3) // 최신순으로 3개 가져오기

        favoriteNoticeAdapter = MyPageFavoriteNoticeAdapter(favoriteNotices)
        binding.rvMyPageFavorite.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteNoticeAdapter
        }
    }

    private fun getFavoriteNotices(): List<CommonNotice> {
        // 여기에서 실제로 즐겨찾기한 공지사항을 가져오는 로직이 필요합니다.
        // 아래는 더미 데이터입니다.
        return listOf(
            CommonNotice("공지사항 1", "2024-01-01", "url1", false, "학사"),
            CommonNotice("공지사항 2", "2024-02-01", "url2", false, "장학"),
            CommonNotice("공지사항 3", "2024-03-01", "url3", false, "학생활동"),
            CommonNotice("공지사항 4", "2024-04-01", "url4", false, "생활관"),
            CommonNotice("공지사항 5", "2024-05-01", "url5", false, "기타")
        )
    }

    override fun initListener() {
        super.initListener()
        binding.ibPageRightFavorite.setOnClickListener {
            (requireActivity() as HomeActivity).replaceFragment(R.id.fl_home, FavoriteFragment(), true)
        }
    }

}