package com.neverland.thinkerbell.presentation.view.home

import android.os.Handler
import com.google.android.material.tabs.TabLayoutMediator
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentHomeBinding
import com.neverland.thinkerbell.domain.model.Notice
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.view.home.adapter.HomeBannerAdapter
import com.neverland.thinkerbell.presentation.view.home.adapter.HomeCategoryVPAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var bannerAdapter: HomeBannerAdapter
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    private val notices = listOf(
        listOf(
            Notice("일반 공지 1", "07.06"),
            Notice("일반 공지 2", "07.06"),
            Notice("일반 공지 3", "07.06")
        ),
        listOf(
            Notice("행사 공지 1", "07.06"),
            Notice("행사 공지 2", "07.06"),
            Notice("행사 공지 3", "07.06")
        ),
        listOf(
            Notice("학사 공지 1", "07.06"),
            Notice("학사 공지 2", "07.06"),
            Notice("학사 공지 3", "07.06")
        ),
        listOf(
            Notice("장학 공지 1", "07.06"),
            Notice("장학 공지 2", "07.06"),
            Notice("장학 공지 3", "07.06")
        ),
        listOf(
            Notice("취업 공지 1", "07.06"),
            Notice("취업 공지 2", "07.06"),
            Notice("취업 공지 3", "07.06")
        ),
        listOf(
            Notice("학생활동 공지 1", "07.06"),
            Notice("학생활동 공지 2", "07.06"),
            Notice("학생활동 공지 3", "07.06")
        ),
        listOf(
            Notice("입찰 공지 1", "07.06"),
            Notice("입찰 공지 2", "07.06"),
            Notice("입찰 공지 3", "07.06")
        ),
        listOf(
            Notice("대학안전 공지 1", "07.06"),
            Notice("대학안전 공지 2", "07.06"),
            Notice("대학안전 공지 3", "07.06")
        ),
        listOf(
            Notice("학칙개정 공지 1", "07.06"),
            Notice("학칙개정 공지 2", "07.06"),
            Notice("학칙개정 공지 3", "07.06")
        ),
        listOf(
            Notice("현장실습지원 공지 1", "07.06"),
            Notice("현장실습지원 공지 2", "07.06"),
            Notice("현장실습지원 공지 3", "07.06")
        ),
        listOf(
            Notice("생활관 공지 1", "07.06"),
            Notice("생활관 공지 2", "07.06"),
            Notice("생활관 공지 3", "07.06")
        ),
        listOf(
            Notice("생활관 입퇴사 공지 1", "07.06"),
            Notice("생활관 입퇴사 공지 2", "07.06"),
            Notice("생활관 입퇴사 공지 3", "07.06")
        ),
        listOf(
            Notice("도서관 공지 1", "07.06"),
            Notice("도서관 공지 2", "07.06"),
            Notice("도서관 공지 3", "07.06")
        ),
        listOf(
            Notice("교직 공지 1", "07.06"),
            Notice("교직 공지 2", "07.06"),
            Notice("교직 공지 3", "07.06")
        )
    )

    override fun initView() {
        setBanner()
        setHomeNoticeRv()
    }

    private fun setBanner(){
        // 배너 설정
        val bannerImages = listOf(R.drawable.img_sample_banner, R.drawable.img_sample_banner, R.drawable.img_sample_banner) // 배너 이미지 리소스 ID 리스트
        bannerAdapter = HomeBannerAdapter(bannerImages)
        binding.vpHomeBanner.adapter = bannerAdapter

        // 페이지 인디케이터 설정
        TabLayoutMediator(binding.tlHomeBannerIndicator, binding.vpHomeBanner) { tab, position -> }.attach()

        // 배너 자동 슬라이드
        autoSlideBanner()
    }

    private fun setHomeNoticeRv(){
        val categories = resources.getStringArray(R.array.category_list)

        // ViewPager2에 어댑터 설정
        val adapter = HomeCategoryVPAdapter(requireActivity(), notices)
        binding.vpHomeNotice.adapter = adapter

        // TabLayout과 ViewPager2를 연결
        TabLayoutMediator(binding.tlHomeCategoryTab, binding.vpHomeNotice) { tab, position ->
            tab.text = categories[position]
        }.attach()
    }

    override fun initListener() {
        super.initListener()
    }

    private fun autoSlideBanner() {
        handler = Handler()
        runnable = object : Runnable {
            override fun run() {
                val currentItem = binding.vpHomeBanner.currentItem
                val nextItem = if (currentItem == bannerAdapter.itemCount - 1) 0 else currentItem + 1
                binding.vpHomeBanner.setCurrentItem(nextItem, true)
                handler.postDelayed(this, 4000)
            }
        }
        handler.postDelayed(runnable, 4000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacks(runnable)
    }
}
