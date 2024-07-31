package com.neverland.thinkerbell.presentation.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.core.utils.LoggerUtil

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
        currentToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
        currentToast?.show()
    }

    protected open fun beforeSetContentView() {}
    protected abstract fun initView()
    protected open fun initListener() {}
    protected open fun setObserver() {}

    protected fun setStatusBarColor(colorId: Int){
        window.statusBarColor = ContextCompat.getColor(this, colorId)
    }

    protected fun replaceFragment(frameLayoutId: Int, fragment: Fragment, isAddBackStack: Boolean){
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(frameLayoutId, fragment)
        if (isAddBackStack) ft.addToBackStack(null)
        ft.commit()
    }
}