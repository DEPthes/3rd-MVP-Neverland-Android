package com.neverland.thinkerbell.presentation.view.home

import android.view.View
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.ActivityHomeBinding
import com.neverland.thinkerbell.presentation.base.BaseActivity
import com.neverland.thinkerbell.presentation.view.category.CategoryFragment
import com.neverland.thinkerbell.presentation.view.myPage.MyPageFragment
import com.neverland.thinkerbell.presentation.view.notice.CommonNoticeFragment

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    override fun initView() {
        showBottomNavigation()
        setStatusBarColor(R.color.primary1, true)
        replaceFragment(R.id.fl_home, HomeFragment(), false)
    }

    override fun initListener() {
        super.initListener()

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(R.id.fl_home, HomeFragment(), false)
                    true
                }
                R.id.navigation_category -> {
                    replaceFragment(R.id.fl_home, CategoryFragment(), false)
                    true
                }
                R.id.navigation_my_page -> {
                    replaceFragment(R.id.fl_home, MyPageFragment(), false)
                    true
                }
                else -> false
            }
        }

        binding.bottomNavigation.setOnItemReselectedListener {  }
    }

    fun hideBottomNavigation() {
        binding.bottomNavigation.visibility = View.GONE
    }

    fun showBottomNavigation() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }
}