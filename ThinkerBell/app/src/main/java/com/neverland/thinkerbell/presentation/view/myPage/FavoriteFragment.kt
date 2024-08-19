package com.neverland.thinkerbell.presentation.view.myPage

import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.FragmentFavoriteBinding
import com.neverland.thinkerbell.presentation.base.BaseFragment
import com.neverland.thinkerbell.presentation.utils.UiState
import com.neverland.thinkerbell.presentation.view.myPage.adapter.FavoriteVPAdapter

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {
    private val favoriteNoticeViewModel: FavoriteNoticeViewModel by viewModels()
    override fun initView() {
        favoriteNoticeViewModel.category.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    // Show loading state if needed
                }
                is UiState.Success -> {
                    setupTabLayout(state.data)
                }
                is UiState.Error -> {
                    // Handle error state
                }

                UiState.Empty -> TODO()
            }
        }
    }

    private fun setupTabLayout(category: List<String>) {
        val adapter = FavoriteVPAdapter(this, category)
        binding.vpAlarmNotice.adapter = adapter

        TabLayoutMediator(binding.tlFavoriteCategoryTab, binding.vpAlarmNotice) { tab, position ->
            tab.text = category[position]
        }.attach()
    }

}
