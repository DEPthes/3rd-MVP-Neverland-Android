package com.neverland.thinkerbell.presentation.view.search

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.text.InputFilter
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
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
import java.util.regex.Pattern

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

        if(allNotices.isEmpty()){
            binding.tlSearchCategoryTab.background = ColorDrawable(ContextCompat.getColor(requireContext(), R.color.primary2))
            binding.tvEmptyView.visibility = View.VISIBLE
            binding.tvSearchResultCount.text = ""
            binding.tvEmptyView.text = "'${binding.etSearch.text}'이(가) 포한된 공지사항을\n찾을 수 없습니다."
            setRecyclerView(emptyList(), emptyMap())
        }

        setEditText()
        searchViewModel.fetchData(allNotices.keys.toList())
    }

    private fun setEditText(){
        val koreanPattern = Pattern.compile("^[a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ\\u318D\\u119E\\u11A2\\u2022\\u2025a\\u00B7\\uFE55]+$")

        binding.etSearch.filters = arrayOf(
            InputFilter { source, start, end, dest, dstart, dend ->
                for (i in start until end) {
                    val char = source[i].toString()
                    if (!koreanPattern.matcher(char).matches()) {
                        return@InputFilter ""
                    }
                }
                null
            },
            InputFilter.LengthFilter(12)
        )
    }

    private fun setRecyclerView(categories: List<NoticeType>, allNotices: Map<NoticeType, List<NoticeItem>>){
        adapter = SearchResultVPAdapter(this, categories = categories, allNotices)

        binding.vpSearchNotice.adapter = adapter

        TabLayoutMediator(binding.tlSearchCategoryTab, binding.vpSearchNotice) { tab, position ->
            tab.text = categories[position].tabName
        }.attach()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initListener() {
        super.initListener()

        binding.etSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
            ) {
                val newSearchWord = binding.etSearch.text.toString()
                if (newSearchWord.length >= 2) {
                    // 검색어를 ViewModel에 추가
                    searchViewModel.addSearchWord(newSearchWord)
                    searchViewModel.searchAllNotices(newSearchWord)
                } else {
                    showToast("2글자 이상 입력해주세요")
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
                    if (newSearchWord.length >= 2) {
                        // 검색어를 ViewModel에 추가
                        searchViewModel.addSearchWord(newSearchWord)
                        searchViewModel.searchAllNotices(newSearchWord)
                    } else {
                        showToast("2글자 이상 입력해주세요")
                    }
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }
    }

    override fun setObserver() {
        super.setObserver()

        searchViewModel.sortState.observe(viewLifecycleOwner){
            when(it){
                is UiState.Loading -> {}
                is UiState.Error -> {}
                is UiState.Empty -> {}
                is UiState.Success -> {
                    setRecyclerView(it.data, allNotices)
                }
            }
        }

        searchViewModel.uiState.observe(viewLifecycleOwner){
            when(it){
                is UiState.Loading -> {}
                is UiState.Error -> {}
                is UiState.Empty -> {
                    binding.tlSearchCategoryTab.background = ColorDrawable(ContextCompat.getColor(requireContext(), R.color.primary2))
                    binding.tvEmptyView.visibility = View.VISIBLE
                    binding.tvSearchResultCount.text = ""
                    binding.tvEmptyView.text = "'${binding.etSearch.text}'이(가) 포한된 공지사항을\n찾을 수 없습니다."
                    setRecyclerView(emptyList(), emptyMap())
                }
                is UiState.Success -> {
                    binding.tvEmptyView.visibility = View.GONE
                    binding.tlSearchCategoryTab.background = ColorDrawable(ContextCompat.getColor(requireContext(), R.color.primary1))
                    binding.tvSearchResultCount.text = "검색 결과 : 총 ${it.data.second.values.sumOf { value -> value.size }}개"
                    setRecyclerView(it.data.second.keys.toList(), it.data.second)
                }
            }
        }
    }
}
