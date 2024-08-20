package com.neverland.thinkerbell.presentation.view.myPage

import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentFavoriteBinding
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.model.notice.NoticeItem
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.utils.UiState
import com.neverland.thinkerbell.presentation.view.myPage.adapter.FavoriteVPAdapter

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    override fun initView() {

    }

    override fun setObserver() {
        super.setObserver()
        favoriteViewModel.notices.observe(viewLifecycleOwner) {
            when(it) {
                is UiState.Loading -> {
                    // Handle loading state
                }
                is UiState.Success -> {
                    setupTabLayout(it.data)
                }
                is UiState.Error -> {
                    // Handle error state
                }
                UiState.Empty -> {

                }
            }
        }
    }

    private fun setupTabLayout(category: Map<NoticeType, List<NoticeItem>>) {
        val keys = category.keys.toList()
        val adapter = FavoriteVPAdapter(this, category)
        binding.vpFavoriteNotice.adapter = adapter

        TabLayoutMediator(binding.tlFavoriteCategoryTab, binding.vpFavoriteNotice) { tab, position ->
            tab.text = keys[position].tabName
        }.attach()
    }

}
