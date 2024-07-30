package com.neverland.thinkerbell.presentation.view

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.ActivityMainBinding
import com.neverland.thinkerbell.presentation.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun initView() {
        window.statusBarColor = ContextCompat.getColor(this, R.color.primary1)
        replaceFragment(HomeFragment(), false)
    }

    override fun initListener() {
        super.initListener()
        // 바텀 내비게이션 설정
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(HomeFragment(), false)
                    true
                }
                R.id.navigation_dashboard -> {
                    //(requireActivity() as MainActivity).replaceFragment(DashboardFragment(), true)
                    true
                }
                R.id.navigation_notifications -> {
                    //(requireActivity() as MainActivity).replaceFragment(NotificationsFragment(), true)
                    true
                }
                else -> false
            }
        }
    }

    override fun setObserver() {
        super.setObserver()
    }
    fun replaceFragment(fragment: Fragment, isAddBackStack: Boolean){
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fl_main)
        if (currentFragment != null && currentFragment::class.java == fragment::class.java) {
            return
        }
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fl_main, fragment)
        if (isAddBackStack) ft.addToBackStack(null)
        ft.commit()
    }
}