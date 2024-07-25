package com.neverland.thinkerbell.presentation.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
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

}