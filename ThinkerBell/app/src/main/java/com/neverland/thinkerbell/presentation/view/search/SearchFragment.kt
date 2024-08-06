package com.neverland.thinkerbell.presentation.view.search

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.data.repository.DataStoreRepositoryImpl
import com.neverland.thinkerbell.databinding.FragmentSearchBinding
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.utils.UiState
import com.neverland.thinkerbell.presentation.view.home.HomeActivity
import com.neverland.thinkerbell.presentation.view.search.adapter.SearchRecentWordAdapter

@SuppressLint("SetTextI18n")
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val dataStoreRepository by lazy { DataStoreRepositoryImpl() }

    private val searchViewModel: SearchViewModel by lazy {
        SearchViewModel(requireActivity().application)
    }
    private lateinit var adapter: SearchRecentWordAdapter

    @SuppressLint("ClickableViewAccessibility")
    override fun initView() {
        // 상단 상태 표시줄 색상 변경
        val window = requireActivity().window
        window.statusBarColor = resources.getColor(R.color.primary2, null)
        val decorView = window.decorView
        val windowInsetsController = ViewCompat.getWindowInsetsController(decorView)
        windowInsetsController?.isAppearanceLightStatusBars = true // 상태 표시줄 아이콘 색상을 흰색으로 설정

        // 바텀 내비게이션 숨기기
        (requireActivity() as HomeActivity).hideBottomNavigation()

        adapter = SearchRecentWordAdapter(
            wordClickListener = { word ->
                binding.etSearch.setText(word)
            },
            deleteClickListener = { word ->
                searchViewModel.deleteSearchWord(word)
            }
        )
        binding.rvRecentSearchWord.layoutManager = LinearLayoutManager(context)
        binding.rvRecentSearchWord.adapter = adapter

        // 소프트 키보드의 검색 버튼 클릭 시 이벤트 처리
        binding.etSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)) {
                val searchWord = binding.etSearch.text.toString()
                if (searchWord.isNotEmpty()) {
                    // 검색어를 ViewModel에 추가
                    searchViewModel.addSearchWord(searchWord)
                    // EditText를 초기화
                    binding.etSearch.text.clear()
                    // 검색어를 전달하며 SearchResultFragment로 이동
                    val fragment = SearchResultFragment.newInstance(searchWord)
                    (requireActivity() as HomeActivity).replaceFragment(R.id.fl_home, fragment, true)
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
                    val searchWord = binding.etSearch.text.toString()
                    if (searchWord.isNotEmpty()) {
                        // 검색어를 ViewModel에 추가
                        searchViewModel.addSearchWord(searchWord)
                        // EditText를 초기화
                        binding.etSearch.text.clear()
                        // 검색어를 전달하며 SearchResultFragment로 이동
                        val fragment = SearchResultFragment.newInstance(searchWord)
                        (requireActivity() as HomeActivity).replaceFragment(R.id.fl_home, fragment, true)
                    }
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }

        // TextWatcher를 추가하여 텍스트 변경 시 이벤트 처리
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                // 현재 터치 리스너는 이미 추가되어 있으므로 필요하지 않음
            }
        })

        searchViewModel.recentSearchWords.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    // Handle loading state
                }
                is UiState.Empty -> {
                    // Handle empty state
                    adapter.submitList(emptyList())
                }
                is UiState.Success -> {
                    // Handle success state
                    adapter.submitList(state.data)
                }
                is UiState.Error -> {
                    // Handle error state
                    // Show error message or similar
                }
            }
        }

        // btn_close 클릭 시 뒤로 가기 기능
        binding.btnClose.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 다른 프래그먼트로 이동 시 상태 표시줄 색상 원래대로 돌리기
        val window = requireActivity().window
        window.statusBarColor = resources.getColor(R.color.primary1, null)
        val decorView = window.decorView
        val windowInsetsController = ViewCompat.getWindowInsetsController(decorView)
        windowInsetsController?.isAppearanceLightStatusBars = false // 상태 표시줄 아이콘 색상을 기본 색상으로 설정

        // 바텀 내비게이션 다시 보이게 하기
        (requireActivity() as HomeActivity).showBottomNavigation()
    }
}
