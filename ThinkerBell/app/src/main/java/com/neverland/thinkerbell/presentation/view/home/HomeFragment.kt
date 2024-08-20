package com.neverland.thinkerbell.presentation.view.home

import android.os.Handler
import androidx.core.content.ContextCompat
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentHomeBinding
import com.neverland.thinkerbell.domain.model.notice.RecentNotices
import com.neverland.thinkerbell.domain.model.univ.Banner
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.utils.UiState
import com.neverland.thinkerbell.presentation.view.alarm.AlarmFragment
import com.neverland.thinkerbell.presentation.view.contact.ContactsFragment
import com.neverland.thinkerbell.presentation.view.deptUrl.DeptUrlFragment
import com.neverland.thinkerbell.presentation.view.home.adapter.HomeBannerAdapter
import com.neverland.thinkerbell.presentation.view.home.adapter.HomeCategoryVPAdapter
import com.neverland.thinkerbell.presentation.view.search.SearchFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var bannerAdapter: HomeBannerAdapter
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    private var lastBackPressedTime: Long = 0
    private val onBackPressedCallback by lazy {
        object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if (System.currentTimeMillis() - lastBackPressedTime < 2000) {
                    requireActivity().finish()
                } else {
                    lastBackPressedTime = System.currentTimeMillis()
                    showToast("한 번 더 누르면 종료됩니다.")
                }
            }
        }
    }

    override fun initView() {
        (requireActivity() as HomeActivity).apply {
            showBottomNavigation()
            setStatusBarColor(R.color.primary1, true)
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
        viewModel.fetchBanners()
        viewModel.fetchRecentNotices()
        viewModel.checkAllAlarm()
    }

    override fun setObserver() {
        super.setObserver()

        viewModel.banners.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {}
                is UiState.Success -> {
                    setBanner(state.data)
                }
                is UiState.Error -> {
                    showToast("배너 조회 실패")
                }
                is UiState.Empty -> {}
            }
        }

        viewModel.uiState.observe(viewLifecycleOwner){
            when (it) {
                is UiState.Loading -> {}
                is UiState.Success -> {
                    setHomeNoticeRv(it.data)
                }
                is UiState.Error -> {
                    showToast("최근 공지 조회 실패")
                }
                is UiState.Empty -> {}
            }
        }

        viewModel.alarmState.observe(viewLifecycleOwner){
            when (it) {
                is UiState.Loading -> {}
                is UiState.Success -> {
                    binding.ivHomeNotificationIcon.setImageResource(if (it.data) R.drawable.ic_topbar_bell_badge else R.drawable.ic_topbar_bell)
                }
                is UiState.Error -> {

                }
                is UiState.Empty -> {}
            }
        }
    }

    private fun setBanner(banners: List<Banner>){
        // 배너 설정
        bannerAdapter = HomeBannerAdapter(banners)
        binding.vpHomeBanner.adapter = bannerAdapter

        // 페이지 인디케이터 설정
        TabLayoutMediator(binding.tlHomeBannerIndicator, binding.vpHomeBanner) { tab, position -> }.attach()

        // 배너 자동 슬라이드
        autoSlideBanner()
    }

    private fun setHomeNoticeRv(notices: RecentNotices){
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
        // 알림 버튼 클릭 시
        binding.ivHomeNotificationIcon.setOnClickListener {
            (requireActivity() as HomeActivity).replaceFragment(R.id.fl_home, AlarmFragment(), true)
        }
        // 검색 버튼 클릭 시
        binding.ivHomeSearchIcon.setOnClickListener {
            (requireActivity() as HomeActivity).replaceFragment(R.id.fl_home, SearchFragment(), true)
        }

        binding.btnHomeDeptPhone.setOnClickListener {
            (requireActivity() as HomeActivity).replaceFragment(R.id.fl_home, ContactsFragment(), true)
        }

        binding.btnHomeDeptHomepage.setOnClickListener {
            (requireActivity() as HomeActivity).replaceFragment(R.id.fl_home, DeptUrlFragment(), true)
        }

        binding.ibHomeCategory.setOnClickListener {
            (requireActivity() as HomeActivity).binding.bottomNavigation.selectedItemId = R.id.navigation_category
        }
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
        onBackPressedCallback.remove()
    }
}
