package com.neverland.thinkerbell.presentation.view.myPage

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentMyPageBinding
import com.neverland.thinkerbell.domain.model.notice.CommonNotice
import com.neverland.thinkerbell.domain.model.notice.NoticeItem
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.utils.UiState
import com.neverland.thinkerbell.presentation.view.home.HomeActivity
import com.neverland.thinkerbell.presentation.view.myPage.adapter.FavoriteNoticeAdapter
import com.neverland.thinkerbell.presentation.view.myPage.adapter.MyPageFavoriteNoticeAdapter
import com.neverland.thinkerbell.presentation.view.myPage.adapter.MyPageKeywordAdapter

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val viewModel: MyPageViewModel by viewModels()
    private lateinit var favoriteNoticeAdapter: MyPageFavoriteNoticeAdapter
    private lateinit var keywordAdapter: MyPageKeywordAdapter

    override fun initView() {
        setupKeywordRecyclerView()
        //setupFavoriteNoticesRecyclerView()
        observeFavoriteNotices()
    }

    private fun setupKeywordRecyclerView() {
        val keywords = listOf("키워드1", "키워드2", "키워드3", "키워드4", "키워드5") // 예시 데이터

        keywordAdapter = MyPageKeywordAdapter(keywords)
        binding.rvMyPageKeyword.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = keywordAdapter
        }
    }

//    private fun setupFavoriteNoticesRecyclerView(list: List<NoticeItem>) {
//        val favoriteNotices = list.sortedByDescending { it. }.take(3) // 최신순으로 3개 가져오기
//
//        favoriteNoticeAdapter = MyPageFavoriteNoticeAdapter(favoriteNotices)
//        binding.rvMyPageFavorite.apply {
//            layoutManager = LinearLayoutManager(requireContext())
//            adapter = favoriteNoticeAdapter
//        }
//    }

    private fun observeFavoriteNotices() {
        viewModel.favoriteNotices.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    // 로딩 상태일 때 필요한 처리 (예: 로딩 스피너 표시)
                }
                is UiState.Success -> {
                    // API 응답이 성공적으로 반환되었을 때 RecyclerView 업데이트
                    //val favoriteNotices = state.data.getAllNotices().sortedByDescending { it.pubDate }.take(3)
                    //favoriteNoticeAdapter.updateNotices(favoriteNotices)
                }
                is UiState.Error -> {
                    // 에러 처리 (예: 에러 메시지 표시)
                }
                UiState.Empty -> {
                    // 빈 상태 처리 (예: 빈 화면 표시)
                }
            }
        }
    }

    override fun initListener() {
        super.initListener()
        binding.ibPageRightFavorite.setOnClickListener {
            (requireActivity() as HomeActivity).replaceFragment(R.id.fl_home, FavoriteFragment(), true)
        }
    }

}