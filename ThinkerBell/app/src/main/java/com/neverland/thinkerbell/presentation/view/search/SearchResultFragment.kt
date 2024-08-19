package com.neverland.thinkerbell.presentation.view.search

import android.annotation.SuppressLint
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentSearchResultBinding
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.model.notice.NoticeItem
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.utils.UiState
import com.neverland.thinkerbell.presentation.view.home.HomeActivity
import com.neverland.thinkerbell.presentation.view.search.adapter.SearchResultVPAdapter

class SearchResultFragment(
    private val searchWord: String,
    private val allNotices: Map<NoticeType, List<NoticeItem>>
) : BaseFragment<FragmentSearchResultBinding>(R.layout.fragment_search_result) {

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var adapter: SearchResultVPAdapter

    @SuppressLint("ClickableViewAccessibility")
    override fun initView() {
        (requireActivity() as HomeActivity).apply {
            setStatusBarColor(R.color.primary1, true)
            showBottomNavigation()
        }

        binding.etSearch.setText(searchWord)
        binding.tvSearchResultCount.text = "검색 결과 : 총 ${allNotices.values.sumOf { it.size }}개"

        setRecyclerView(allNotices.keys.toList(), allNotices)
    }

    private fun setRecyclerView(categories: List<NoticeType>, allNotices: Map<NoticeType, List<NoticeItem>>){
        adapter = SearchResultVPAdapter(this, categories = categories, allNotices)

        binding.vpSearchNotice.adapter = adapter

        TabLayoutMediator(binding.tlSearchCategoryTab, binding.vpSearchNotice) { tab, position ->
            tab.text = categories[position].tabName
        }.attach()
    }

    override fun initListener() {
        super.initListener()

        binding.etSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
            ) {
                val newSearchWord = binding.etSearch.text.toString()
                if (newSearchWord.isNotEmpty()) {
                    // 검색어를 ViewModel에 추가
                    searchViewModel.addSearchWord(newSearchWord)
                    searchViewModel.searchAllNotices(newSearchWord)
                }
                true
            } else {
                false
            }
        }

        // 터치 리스너를 추가하여 drawableEnd 클릭 이벤트 처리
        binding.etSearch.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                // EditText의 오른쪽 패딩 값을 고려하여 터치 위치를 계산
                val drawableEndWidth = binding.etSearch.compoundDrawables[2]?.bounds?.width() ?: 0
                val padding = binding.etSearch.paddingEnd

                if (event.rawX >= (binding.etSearch.right - drawableEndWidth - padding) &&
                    event.rawX <= (binding.etSearch.right - padding)
                ) {
                    val newSearchWord = binding.etSearch.text.toString()
                    if (newSearchWord.isNotEmpty()) {
                        // 검색어를 ViewModel에 추가
                        searchViewModel.addSearchWord(newSearchWord)
                        searchViewModel.searchAllNotices(newSearchWord)
                    }
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }
    }

    override fun setObserver() {
        super.setObserver()

        searchViewModel.uiState.observe(viewLifecycleOwner){
            when(it){
                is UiState.Loading -> {}
                is UiState.Error -> {}
                is UiState.Empty -> {}
                is UiState.Success -> {
                    binding.tvSearchResultCount.text = "검색 결과 : 총 ${it.data.second.values.sumOf { value -> value.size }}개"
                    setRecyclerView(it.data.second.keys.toList(), it.data.second)
                }
            }
        }
    }
}
