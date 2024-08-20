package com.neverland.thinkerbell.presentation.view.notice

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.core.utils.LoggerUtil
import com.neverland.thinkerbell.databinding.FragmentCommonNoticeBinding
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.model.notice.NoticeItem
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.custom.CustomDividerDecoration
import com.neverland.thinkerbell.presentation.utils.UiState
import com.neverland.thinkerbell.presentation.view.OnRvItemClickListener
import com.neverland.thinkerbell.presentation.view.home.HomeActivity
import com.neverland.thinkerbell.presentation.view.home.HomeFragment

@SuppressLint("SetTextI18n")
class
CommonNoticeFragment(
    private val noticeType: NoticeType
) : BaseFragment<FragmentCommonNoticeBinding>(R.layout.fragment_common_notice) {

    private val viewModel: CommonNoticeViewModel by viewModels()
    private val commonNoticeAdapter: CommonRvAdapter by lazy { CommonRvAdapter(noticeType).apply {
        setRvItemClickListener(object : OnRvItemClickListener<String>{
            override fun onClick(item: String) {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(item)
                }
                startActivity(intent)
            }
        })
        setBookmarkClickListener(object : OnRvItemClickListener<Pair<Int, Boolean>>{
            override fun onClick(item: Pair<Int, Boolean>) {
                if(item.second){
                    viewModel.postBookmark(category = noticeType, noticeId = item.first)
                } else {
                    viewModel.deleteBookmark(category = noticeType, noticeId = item.first)
                }
            }
        })
    } }
    private lateinit var spinnerAdapter: CampusSpinnerAdapter
    private val spinnerRequiredNotices = listOf(NoticeType.DORMITORY_NOTICE, NoticeType.DORMITORY_ENTRY_NOTICE, NoticeType.LIBRARY_NOTICE)

    override fun initView() {
        (requireActivity() as HomeActivity).apply{
            hideBottomNavigation()
            setStatusBarColor(R.color.primary1, true)
        }
        binding.tvNoticeTitle.text = noticeType.koName
        binding.groupNoticeSearchView.visibility = View.GONE
        setupRecyclerView()

        if(spinnerRequiredNotices.contains(noticeType)) setCampusSpinner() else binding.spinnerCampus.visibility = View.GONE
    }

    private fun setupRecyclerView() {
        binding.rvNotice.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(CustomDividerDecoration(requireContext(), "#404040", 1f ))
            adapter = commonNoticeAdapter
            itemAnimator = null
        }
        viewModel.fetchData(noticeType, 0)
    }

    override fun initListener() {
        super.initListener()
        setupButtonListeners()
        setupSearchListener()
    }

    private fun setupButtonListeners() {
        binding.ibMenu.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        binding.ibHome.setOnClickListener {
            (requireActivity() as HomeActivity).replaceFragment(
                frameLayoutId = R.id.fl_home,
                fragment = HomeFragment(),
                isAddBackStack = false,
                isStackClear = true
            )
        }

        binding.btnBack.setOnClickListener {
            showNoticePage()
        }

        binding.ibPageRight1.setOnClickListener { viewModel.fetchData(noticeType, viewModel.currentPage.value!! + 1) }
        binding.ibPageRight2.setOnClickListener { viewModel.fetchData(noticeType, viewModel.currentPage.value!! + 10) }
        binding.ibPageLeft1.setOnClickListener { viewModel.fetchData(noticeType, viewModel.currentPage.value!! - 1) }
        binding.ibPageLeft2.setOnClickListener { viewModel.fetchData(noticeType, viewModel.currentPage.value!! - 10) }
    }

    private fun setupSearchListener() {
        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.currentNotice = commonNoticeAdapter.currentList
                viewModel.searchNotice(noticeType, binding.etSearch.text.toString())
                true
            } else {
                false
            }
        }

        binding.etSearch.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEndWidth = binding.etSearch.compoundDrawables[2]?.bounds?.width() ?: 0
                val padding = binding.etSearch.paddingEnd

                if (event.rawX >= (binding.etSearch.right - drawableEndWidth - padding) &&
                    event.rawX <= (binding.etSearch.right - padding)) {
                    val searchWord = binding.etSearch.text.toString()
                    if (searchWord.isNotEmpty()) {
                        viewModel.currentNotice = commonNoticeAdapter.currentList
                        viewModel.searchNotice(noticeType, binding.etSearch.text.toString())
                    }
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false
        }
    }

    private fun showNoticePage() {
        binding.groupNoticeSearchView.visibility = View.GONE
        binding.llNoticePage.visibility = View.VISIBLE
        binding.tvNoticePage.text = "${viewModel.currentPage.value!!+1}/${viewModel.totalPage}"
        commonNoticeAdapter.submitList(viewModel.currentNotice)
    }

    override fun setObserver() {
        super.setObserver()
        viewModel.uiState.observe(viewLifecycleOwner, ::handleUiState)
        viewModel.searchState.observe(viewLifecycleOwner, ::handleSearchState)
        viewModel.currentPage.observe(viewLifecycleOwner){
            updatePageButtons(it + 1)
        }
        viewModel.toastState.observe(viewLifecycleOwner, ::handleToastState)
    }

    private fun handleToastState(state: UiState<String>){
        when (state) {
            is UiState.Loading -> {}
            is UiState.Error -> {}
            is UiState.Empty -> {}
            is UiState.Success -> {
                showToast(state.data)
            }
        }
    }

    private fun handleUiState(state: UiState<List<NoticeItem>>) {
        when (state) {
            is UiState.Loading -> {}
            is UiState.Error -> {
                showToast("공지 조회 실패")
            }
            is UiState.Empty -> {}
            is UiState.Success -> {
                commonNoticeAdapter.submitList(state.data)
                binding.tvNoticePage.text = "${viewModel.currentPage.value!! + 1}/${viewModel.totalPage}"
                updatePageButtons(viewModel.currentPage.value!! + 1)
            }
        }
    }

    private fun handleSearchState(state: UiState<List<NoticeItem>>) {
        when (state) {
            is UiState.Loading -> { /* Show loading state if needed */ }
            is UiState.Error -> { /* Show error state if needed */ }
            is UiState.Empty -> { /* Show empty state if needed */ }
            is UiState.Success -> {
                commonNoticeAdapter.submitList(state.data)
                binding.groupNoticeSearchView.visibility = View.VISIBLE
                binding.llNoticePage.visibility = View.GONE
                binding.tvSearchNoticeResult.text = "'${binding.etSearch.text}'이(가) 포함된 공지사항 (${state.data.size}개)"
                binding.etSearch.text.clear()
            }
        }
    }

    private fun updatePageButtons(currentPage: Int) {
        LoggerUtil.i(currentPage.toString())
        when {
            currentPage == 1 -> setClickablePageButtons(1)
            currentPage / 10 == 0 -> setClickablePageButtons(2)
            currentPage / 10 >= 1 -> setClickablePageButtons(3)
            currentPage == viewModel.totalPage -> setClickablePageButtons(4)
        }
    }

    private fun setClickablePageButtons(type: Int) {
        val context = requireContext()
        val grayColor = ContextCompat.getColor(context, R.color.gray_ACACAD)
        val redColor = ContextCompat.getColor(context, R.color.red_gray_700)

        binding.apply {
            when (type) {
                1 -> {
                    // 첫 페이지 일 때
                    LoggerUtil.i("btn type: 1")
                    setButtonState(ibPageLeft1, grayColor)
                    setButtonState(ibPageLeft2, grayColor)
                    setButtonState(ibPageRight1, if (viewModel.totalPage >= 2) redColor else grayColor)
                    setButtonState(ibPageRight2, if (viewModel.totalPage / 10 >= 1) redColor else grayColor)
                }
                2 -> {
                    // 현재 페이지 숫자가 한 자리 일 때
                    LoggerUtil.i("btn type: 2")
                    setButtonState(ibPageLeft1, redColor)
                    setButtonState(ibPageLeft2, grayColor)
                    setButtonState(ibPageRight1, if (viewModel.totalPage > viewModel.currentPage.value!!+1) redColor else grayColor)
                    setButtonState(ibPageRight2, if (viewModel.totalPage - viewModel.currentPage.value!!+1 >= 10) redColor else grayColor)
                }
                3 -> {
                    // 현재 페이지 숫자가 두 자리 이상 일 때
                    LoggerUtil.i("btn type: 3")
                    setButtonState(ibPageLeft1, redColor)
                    setButtonState(ibPageLeft2, if (viewModel.currentPage.value!!+1 / 11 >= 1) redColor else grayColor)
                    setButtonState(ibPageRight1, if (viewModel.totalPage > viewModel.currentPage.value!!+1) redColor else grayColor)
                    setButtonState(ibPageRight2, if (viewModel.totalPage - viewModel.currentPage.value!!+1 >= 10) redColor else grayColor)
                }
                4 -> {
                    // 마지막 페이지 일 때
                    LoggerUtil.i("btn type: 4")
                    setButtonState(ibPageLeft1, redColor)
                    setButtonState(ibPageLeft2, if (viewModel.currentPage.value!!+1 / 11 >= 1) redColor else grayColor)
                    setButtonState(ibPageRight1, grayColor)
                    setButtonState(ibPageRight2, grayColor)
                }
            }
        }
    }

    private fun setButtonState(button: ImageButton, color: Int) {
        button.imageTintList = ColorStateList.valueOf(color)
        button.isClickable = color == ContextCompat.getColor(requireContext(), R.color.red_gray_700)
    }

    private fun setCampusSpinner() {
        val categories = listOf("전체", "인문", "자연")

        spinnerAdapter = CampusSpinnerAdapter(requireContext(), binding.spinnerCampus, R.layout.item_spinner_campus, categories)
        binding.spinnerCampus.adapter = spinnerAdapter
        binding.spinnerCampus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val value = binding.spinnerCampus.getItemAtPosition(position).toString()
                LoggerUtil.i(value)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        binding.spinnerCampus.dropDownVerticalOffset = 110
        binding.spinnerCampus.dropDownHorizontalOffset = -30
    }
}
