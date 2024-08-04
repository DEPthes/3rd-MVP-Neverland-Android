package com.neverland.thinkerbell.presentation.view.search

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentSearchResultNoticeBinding
import com.neverland.thinkerbell.domain.model.notice.DummyData
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.view.notice.SearchResultNoticeAdapter

class SearchResultNoticeFragment : BaseFragment<FragmentSearchResultNoticeBinding>(R.layout.fragment_search_result_notice) {

    private lateinit var category: String
    private lateinit var searchWord: String
    private val noticeAdapter = SearchResultNoticeAdapter()
    private var noticesCount: Int = 0

    override fun initView() {
        arguments?.let {
            category = it.getString(ARG_CATEGORY) ?: ""
            searchWord = it.getString(ARG_SEARCH_WORD) ?: ""
        }
        setupRecyclerView()
        filterNotices()
    }

    private fun setupRecyclerView() {
        binding.rvNoticeList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noticeAdapter
        }
    }

    private fun filterNotices() {
        val filteredNotices = DummyData.notices.flatten().filter {
            it.classification == category
        }
        noticeAdapter.submitList(filteredNotices)
        noticesCount = filteredNotices.size
    }

    fun getNoticesCount(): Int {
        return noticesCount
    }

    companion object {
        private const val ARG_CATEGORY = "category"
        private const val ARG_SEARCH_WORD = "search_word"

        fun newInstance(category: String, searchWord: String) = SearchResultNoticeFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_CATEGORY, category)
                putString(ARG_SEARCH_WORD, searchWord)
            }
        }
    }
}
