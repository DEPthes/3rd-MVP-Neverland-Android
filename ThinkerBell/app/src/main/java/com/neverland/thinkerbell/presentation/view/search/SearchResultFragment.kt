package com.neverland.thinkerbell.presentation.view.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentSearchResultBinding
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.view.search.adapter.SearchResultVPAdapter

class SearchResultFragment : BaseFragment<FragmentSearchResultBinding>(R.layout.fragment_search_result) {

    private val searchViewModel: SearchViewModel by viewModels()

    private lateinit var adapter: SearchResultVPAdapter

    @SuppressLint("ClickableViewAccessibility")
    override fun initView() {
        val categories = resources.getStringArray(R.array.category_list).toList()
        val searchWord = arguments?.getString(ARG_SEARCH_WORD) ?: ""
        adapter = SearchResultVPAdapter(this, categories, searchWord) { count ->
            updateSearchResultCount(count)
        }

        binding.vpSearchNotice.adapter = adapter

        TabLayoutMediator(binding.tlSearchCategoryTab, binding.vpSearchNotice) { tab, position ->
            tab.text = categories[position]
        }.attach()

        binding.etSearch.setText(searchWord)

        // EditText의 EditorActionListener를 설정하여 검색 버튼 클릭 시 이벤트 처리
        binding.etSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {
                val newSearchWord = binding.etSearch.text.toString()
                if (newSearchWord.isNotEmpty()) {
                    // 검색어를 ViewModel에 추가
                    searchViewModel.addSearchWord(newSearchWord)
                    updateSearchWord(newSearchWord)
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
                    event.rawX <= (binding.etSearch.right - padding)) {
                    val newSearchWord = binding.etSearch.text.toString()
                    if (newSearchWord.isNotEmpty()) {
                        // 검색어를 ViewModel에 추가
                        searchViewModel.addSearchWord(newSearchWord)
                        updateSearchWord(newSearchWord)
                    }
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }

        // 검색 결과 개수를 업데이트
        searchViewModel.searchResultCount.observe(viewLifecycleOwner) { count ->
            binding.tvSearchResultCount.text = "검색 결과 : 총 ${count}개"
        }
    }

    private fun updateSearchWord(newSearchWord: String) {
        val categories = resources.getStringArray(R.array.category_list).toList()
        adapter = SearchResultVPAdapter(this, categories, newSearchWord) { count ->
            updateSearchResultCount(count)
        }
        binding.vpSearchNotice.adapter = adapter

        TabLayoutMediator(binding.tlSearchCategoryTab, binding.vpSearchNotice) { tab, position ->
            tab.text = categories[position]
        }.attach()
    }

    private fun updateSearchResultCount(count: Int) {
        searchViewModel.updateSearchResultCount(count)
    }

    companion object {
        private const val ARG_SEARCH_WORD = "search_word"

        fun newInstance(searchWord: String): SearchResultFragment {
            val fragment = SearchResultFragment()
            val args = Bundle().apply {
                putString(ARG_SEARCH_WORD, searchWord)
            }
            fragment.arguments = args
            return fragment
        }
    }
}
