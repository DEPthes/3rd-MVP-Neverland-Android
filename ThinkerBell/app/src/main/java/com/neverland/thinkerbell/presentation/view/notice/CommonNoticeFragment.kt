package com.neverland.thinkerbell.presentation.view.notice

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentCommonNoticeBinding
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.model.notice.CommonNotice
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.utils.UiState

@SuppressLint("SetTextI18n")
class
CommonNoticeFragment(
    private val noticeType: NoticeType
) : BaseFragment<FragmentCommonNoticeBinding>(R.layout.fragment_common_notice) {

    private val viewModel: CommonNoticeViewModel by viewModels()
    private val commonNoticeAdapter: CommonRvAdapter by lazy { CommonRvAdapter() }

    override fun initView() {
        binding.tvNoticeTitle.text = noticeType.title
        binding.groupNoticeSearchView.visibility = View.GONE
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.rvNotice.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = commonNoticeAdapter
        }
        viewModel.fetchData(noticeType, 1)
    }

    override fun initListener() {
        super.initListener()
        setupButtonListeners()
        setupSearchListener()
    }

    private fun setupButtonListeners() {
        binding.btnFab.setOnClickListener {

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
                viewModel.searchNotice(noticeType)
                true
            } else {
                false
            }
        }
    }

    private fun showNoticePage() {
        binding.groupNoticeSearchView.visibility = View.GONE
        binding.llNoticePage.visibility = View.VISIBLE
        binding.tvNoticePage.text = "${viewModel.currentPage.value}/${viewModel.totalPage}"
        commonNoticeAdapter.submitList(viewModel.currentNotice)
    }

    override fun setObserver() {
        super.setObserver()
        viewModel.uiState.observe(viewLifecycleOwner, ::handleUiState)
        viewModel.searchState.observe(viewLifecycleOwner, ::handleSearchState)
        viewModel.currentPage.observe(viewLifecycleOwner, ::updatePageButtons)
    }

    private fun handleUiState(state: UiState<List<CommonNotice>>) {
        when (state) {
            is UiState.Loading -> { /* Show loading state if needed */ }
            is UiState.Error -> { /* Show error state if needed */ }
            is UiState.Empty -> { /* Show empty state if needed */ }
            is UiState.Success -> {
                commonNoticeAdapter.submitList(state.data)
                binding.tvNoticePage.text = "${viewModel.currentPage.value}/${viewModel.totalPage}"
            }
        }
    }

    private fun handleSearchState(state: UiState<List<CommonNotice>>) {
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
                    setButtonState(ibPageLeft1, grayColor)
                    setButtonState(ibPageLeft2, grayColor)
                    setButtonState(ibPageRight1, if (viewModel.totalPage >= 2) redColor else grayColor)
                    setButtonState(ibPageRight2, if (viewModel.totalPage / 10 >= 1) redColor else grayColor)
                }
                2 -> {
                    // 현재 페이지 숫자가 한 자리 일 때
                    setButtonState(ibPageLeft1, redColor)
                    setButtonState(ibPageLeft2, grayColor)
                    setButtonState(ibPageRight1, if (viewModel.totalPage > viewModel.currentPage.value!!) redColor else grayColor)
                    setButtonState(ibPageRight2, if (viewModel.totalPage - viewModel.currentPage.value!! >= 10) redColor else grayColor)
                }
                3 -> {
                    // 현재 페이지 숫자가 두 자리 이상 일 때
                    setButtonState(ibPageLeft1, redColor)
                    setButtonState(ibPageLeft2, if (viewModel.currentPage.value!! / 11 >= 1) redColor else grayColor)
                    setButtonState(ibPageRight1, if (viewModel.totalPage > viewModel.currentPage.value!!) redColor else grayColor)
                    setButtonState(ibPageRight2, if (viewModel.totalPage - viewModel.currentPage.value!! >= 10) redColor else grayColor)
                }
                4 -> {
                    // 마지막 페이지 일 때
                    setButtonState(ibPageLeft1, redColor)
                    setButtonState(ibPageLeft2, if (viewModel.currentPage.value!! / 11 >= 1) redColor else grayColor)
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
}
