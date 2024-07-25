package com.neverland.thinkerbell.presentation.base

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.neverland.thinkerbell.core.utils.LoggerUtil

abstract class BaseFragment<T : ViewDataBinding>(@LayoutRes val layoutId : Int): Fragment() {
    private var _binding : T? = null
    protected val binding get() = _binding!!

    private var currentToast: Toast? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        initView()
        initListener()
        setObserver()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    protected abstract fun initView()

    protected open fun initListener() {}

    protected open fun setObserver() {}

    protected fun showToast(msg: String) {
        currentToast?.cancel()
        currentToast = Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT)
        currentToast?.show()
    }
}