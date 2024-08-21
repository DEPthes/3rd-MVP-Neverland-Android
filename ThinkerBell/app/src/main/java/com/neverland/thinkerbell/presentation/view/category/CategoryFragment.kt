package com.neverland.thinkerbell.presentation.view.category

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentCategoryBinding
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.model.Notice
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.utils.UiState
import com.neverland.thinkerbell.presentation.view.OnRvItemClickListener
import com.neverland.thinkerbell.presentation.view.home.HomeActivity
import com.neverland.thinkerbell.presentation.view.notice.CommonNoticeFragment

class CategoryFragment: BaseFragment<FragmentCategoryBinding>(R.layout.fragment_category) {
    private val viewModel: CategoryViewModel by viewModels()
    private lateinit var categoryRvAdapter: CategoryRvAdapter

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
            setStatusBarColor(R.color.primary2, false)
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
        setRvAdapter()
        viewModel.fetchData()
    }

    private fun setRvAdapter(){
        categoryRvAdapter = CategoryRvAdapter().apply {
            setRvItemClickListener(object : OnRvItemClickListener<NoticeType>{
                override fun onClick(item: NoticeType) {
                    (requireActivity() as HomeActivity).replaceFragment(
                        fragment = CommonNoticeFragment(item),
                        frameLayoutId = R.id.fl_home,
                        isAddBackStack = true
                    )
                }
            })
            setListOrderChangeListener(object : OnRvItemClickListener<List<NoticeType>>{
                override fun onClick(item: List<NoticeType>) {
                    viewModel.saveCategoryOrder(item)
                }
            })
        }
        binding.rvCategory.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCategory.adapter = categoryRvAdapter

        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(binding.rvCategory))
        itemTouchHelper.attachToRecyclerView(binding.rvCategory)
    }

    override fun setObserver() {
        super.setObserver()

        viewModel.uiState.observe(viewLifecycleOwner){
            when(it){
                is UiState.Loading -> {}
                is UiState.Empty -> {}
                is UiState.Error -> {}
                is UiState.Success -> {
                    categoryRvAdapter.submitList(it.data)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        onBackPressedCallback.remove()
    }
}