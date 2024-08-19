package com.neverland.thinkerbell.presentation.view.category

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentCategoryBinding
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.utils.CustomLongDividerItemDecoration
import com.neverland.thinkerbell.presentation.utils.UiState
import com.neverland.thinkerbell.presentation.view.OnRvItemClickListener
import com.neverland.thinkerbell.presentation.view.home.HomeActivity
import com.neverland.thinkerbell.presentation.view.notice.CommonNoticeFragment

class CategoryFragment: BaseFragment<FragmentCategoryBinding>(R.layout.fragment_category) {
    private val viewModel: CategoryViewModel by viewModels()
    private lateinit var categoryRvAdapter: CategoryRvAdapter


    override fun initView() {
        (requireActivity() as HomeActivity).apply {
            showBottomNavigation()
            setStatusBarColor(R.color.primary2, false)
        }
        setRvAdapter()
        viewModel.fetchData()
    }

    private fun setRvAdapter(){
        categoryRvAdapter = CategoryRvAdapter().apply {
            setRvItemClickListener(object : OnRvItemClickListener<NoticeType>{
                override fun onClick(item: NoticeType) {
                    viewModel.saveCategoryOrder(categoryRvAdapter.currentList.toList())

                    (requireActivity() as HomeActivity).replaceFragment(
                        fragment = CommonNoticeFragment(item),
                        frameLayoutId = R.id.fl_home,
                        isAddBackStack = true
                    )
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
}