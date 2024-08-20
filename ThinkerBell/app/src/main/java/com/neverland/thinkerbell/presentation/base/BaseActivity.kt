package com.neverland.thinkerbell.presentation.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.neverland.thinkerbell.core.utils.LoggerUtil
import com.neverland.thinkerbell.presentation.custom.CustomToast

abstract class BaseActivity<T: ViewDataBinding>(@LayoutRes private val layoutId: Int): AppCompatActivity() {
    lateinit var binding: T
    private var currentToast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        beforeSetContentView()
        super.onCreate(savedInstanceState)
        LoggerUtil.d("onCreate: $localClassName")

        binding = DataBindingUtil.setContentView(this, layoutId)
        initView()
        initListener()
        setObserver()
    }

    override fun onDestroy() {
        super.onDestroy()
        LoggerUtil.d("onDestroy: $localClassName")
    }

    protected fun showToast(msg: String) {
        currentToast?.cancel()
        currentToast = CustomToast.makeToast(this, msg)
        currentToast?.show()
    }

    protected open fun beforeSetContentView() {}
    protected abstract fun initView()
    protected open fun initListener() {}
    protected open fun setObserver() {}

    fun setStatusBarColor(colorId: Int, isLightIcon: Boolean){
        window.statusBarColor = ContextCompat.getColor(this, colorId)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = !isLightIcon
        }
    }

     fun replaceFragment(frameLayoutId: Int, fragment: Fragment, isAddBackStack: Boolean, isStackClear: Boolean = false){
         val fragmentManager = supportFragmentManager
         if(isStackClear) fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

         val ft = fragmentManager.beginTransaction()
         ft.replace(frameLayoutId, fragment)
         if (isAddBackStack) ft.addToBackStack(null)
         ft.commit()
    }
}