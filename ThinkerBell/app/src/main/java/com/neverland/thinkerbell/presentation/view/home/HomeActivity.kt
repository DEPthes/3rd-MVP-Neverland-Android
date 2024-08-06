package com.neverland.thinkerbell.presentation.view.home

import android.view.View
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.ActivityHomeBinding
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.presentation.base.BaseActivity
import com.neverland.thinkerbell.presentation.view.notice.CommonNoticeFragment

class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    override fun initView() {
        setStatusBarColor(R.color.primary1)
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
                R.id.navigation_dashboard -> {
                    replaceFragment(R.id.fl_home, CommonNoticeFragment(NoticeType.STUDENT_ACTIVITIES), false)
                    true
                }
                R.id.navigation_notifications -> {
                    //replaceFragment(R.id.fl_home, NotificationsFragment(), false)
                    true
                }
                else -> false
            }
        }

        binding.bottomNavigation.setOnItemReselectedListener {  }
    }

    fun hideBottomNavigation() {
        findViewById<View>(R.id.bottom_navigation).visibility = View.GONE
    }

    fun showBottomNavigation() {
        findViewById<View>(R.id.bottom_navigation).visibility = View.VISIBLE
    }

}