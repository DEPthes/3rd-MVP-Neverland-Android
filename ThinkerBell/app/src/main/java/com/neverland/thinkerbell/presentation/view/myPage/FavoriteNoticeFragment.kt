package com.neverland.thinkerbell.presentation.view.myPage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentFavoriteNoticeBinding
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.view.myPage.adapter.FavoriteNoticeAdapter

class FavoriteNoticeFragment : BaseFragment<FragmentFavoriteNoticeBinding>(R.layout.fragment_favorite_notice) {

    private val noticeAdapter = FavoriteNoticeAdapter()
    private val viewModel: FavoriteNoticeViewModel by viewModels()
    companion object {
        private const val ARG_CATEGORY = "category"

        fun newInstance(category: String) = FavoriteNoticeFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_CATEGORY, category)
            }
        }
    }

    private lateinit var category: String

    override fun initView() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        category = arguments?.getString(ARG_CATEGORY) ?: ""
        binding.rvNoticeList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noticeAdapter
        }

        // Observe the notices and update the adapter
        viewModel.notices.observe(viewLifecycleOwner) { notices ->
            val filteredNotices = notices[category] ?: emptyList()
            noticeAdapter.submitList(filteredNotices)
        }
    }

}