package com.neverland.thinkerbell.presentation.view.search

import android.annotation.SuppressLint
import android.text.InputFilter
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentSearchBinding
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.utils.UiState
import com.neverland.thinkerbell.presentation.view.home.HomeActivity
import com.neverland.thinkerbell.presentation.view.search.adapter.SearchRecentWordAdapter
import java.util.regex.Pattern

@SuppressLint("SetTextI18n")
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val searchViewModel: SearchViewModel by lazy { SearchViewModel() }
    private lateinit var adapter: SearchRecentWordAdapter

    @SuppressLint("ClickableViewAccessibility")
    override fun initView() {
        (requireActivity() as HomeActivity).apply {
            hideBottomNavigation()
            setStatusBarColor(R.color.primary2, false)
        }

        setEditText()
        setRecyclerView()
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

    private fun setRecyclerView(){
        adapter = SearchRecentWordAdapter(
            wordClickListener = { word ->
                binding.etSearch.setText(word)
                showKeyboardAndFocus(binding.etSearch)
            },
            deleteClickListener = { word ->
                searchViewModel.deleteSearchWord(word)
            }
        )
        binding.rvRecentSearchWord.layoutManager = LinearLayoutManager(context)
        binding.rvRecentSearchWord.adapter = adapter
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initListener() {
        super.initListener()

        binding.btnClose.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.etSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {
                val searchWord = binding.etSearch.text.toString()
                if (searchWord.length >= 2) {
                    // 검색어를 ViewModel에 추가
                    searchViewModel.addSearchWord(searchWord)
                    // 검색어를 전달하며 SearchResultFragment로 이동
                    searchViewModel.searchAllNotices(searchWord)
                } else {
                    showToast("2글자 이상 입력해주세요")
                }
                true
            } else {
                false
            }
        }

        // 터치 리스너를 추가하여 drawableEnd 클릭 이벤트 처리
        binding.etSearch.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                // EditText의 오른쪽 패딩 값을 고려하여 터치 위치를 계산
                val drawableEndWidth = binding.etSearch.compoundDrawables[2]?.bounds?.width() ?: 0
                val padding = binding.etSearch.paddingEnd

                if (event.rawX >= (binding.etSearch.right - drawableEndWidth - padding) &&
                    event.rawX <= (binding.etSearch.right - padding)) {
                    val searchWord = binding.etSearch.text.toString()
                    if (searchWord.length >= 2) {
                        // 검색어를 ViewModel에 추가
                        searchViewModel.addSearchWord(searchWord)
                        // 검색어를 전달하며 SearchResultFragment로 이동
                        searchViewModel.searchAllNotices(searchWord)
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

        searchViewModel.recentSearchWords.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                }
                is UiState.Empty -> {
                    adapter.submitList(emptyList())
                }
                is UiState.Success -> {
                    adapter.submitList(state.data)
                }
                is UiState.Error -> {

                }
            }
        }

        searchViewModel.uiState.observe(viewLifecycleOwner){
            when(it){
                is UiState.Loading -> {
                }
                is UiState.Empty -> {
                    searchViewModel.setUiStateLoading()
                    val fragment = SearchResultFragment(binding.etSearch.text.toString(), emptyMap())
                    binding.etSearch.text.clear()
                    (requireActivity() as HomeActivity).replaceFragment(R.id.fl_home, fragment, true)
                }
                is UiState.Success -> {
                    searchViewModel.setUiStateLoading()
                    val fragment = SearchResultFragment(it.data.first, it.data.second)
                    binding.etSearch.text.clear()
                    (requireActivity() as HomeActivity).replaceFragment(R.id.fl_home, fragment, true)
                }
                is UiState.Error -> {}
            }
        }
    }
}
