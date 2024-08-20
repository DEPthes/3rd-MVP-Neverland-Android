package com.neverland.thinkerbell.presentation.view.myPage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentKeywordManageBinding
import com.neverland.thinkerbell.domain.model.keyword.Keyword
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.utils.UiState
import com.neverland.thinkerbell.presentation.view.OnRvItemClickListener
import com.neverland.thinkerbell.presentation.view.home.HomeActivity
import com.neverland.thinkerbell.presentation.view.myPage.adapter.KeywordManagementAdapter

class KeywordManageFragment : BaseFragment<FragmentKeywordManageBinding>(R.layout.fragment_keyword_manage) {

    private val myPageviewModel: MyPageViewModel by viewModels()
    private lateinit var keywordManagementAdapter: KeywordManagementAdapter
    override fun initView() {
        (requireActivity() as HomeActivity).apply {
            setStatusBarColor(R.color.primary1, true)
            hideBottomNavigation()
        }
    }

    override fun setObserver() {
        super.setObserver()

        myPageviewModel.keyword.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Loading -> {
                    // Handle loading state
                }

                is UiState.Success -> {
                    setupKeywordRecyclerView(it.data)
                    binding.tvKeywordCount.text = "${it.data.size} / 9"
                }

                is UiState.Error -> {
                    // Handle error state
                }

                UiState.Empty -> {

                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        myPageviewModel.fetchKeyword()
    }

    override fun initListener() {
        super.initListener()
        binding.btnKeywordAdd.setOnClickListener {
            (requireActivity() as HomeActivity).replaceFragment(R.id.fl_home, KeywordAddFragment(), true)
        }
    }

    private fun setupKeywordRecyclerView(list: List<Keyword>) {
        keywordManagementAdapter = KeywordManagementAdapter(list).apply {
            setOnRvItemClickListener(object : OnRvItemClickListener<String>{
                override fun onClick(item: String) {
                    myPageviewModel.deleteKeyword(item)
                    keywordManagementAdapter.deleteKeyword(item)
                    binding.tvKeywordCount.text = "${keywordManagementAdapter.itemCount} / 9"
                }
            })

        }
        binding.rvKeywordManagement.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = keywordManagementAdapter
        }
    }
}