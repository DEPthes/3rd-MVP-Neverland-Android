package com.neverland.thinkerbell.presentation.base

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.neverland.thinkerbell.presentation.custom.CustomToast

abstract class BaseFragment<T : ViewDataBinding>(@LayoutRes val layoutId : Int): Fragment() {
    private var _binding : T? = null
    protected val binding get() = _binding!!

    private var currentToast: Toast? = null

    private val keyboardListener = object : ViewTreeObserver.OnGlobalLayoutListener {
        private val rect = Rect()

        override fun onGlobalLayout() {
            val rootView = view?.rootView ?: return
            rootView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = rootView.height
            val keypadHeight = screenHeight - rect.bottom

            if (keypadHeight > screenHeight * 0.15) {
                onKeyboardVisibilityChanged(true)
            } else {
                onKeyboardVisibilityChanged(false)
            }
        }
    }

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
        currentToast = CustomToast.makeToast(requireContext(), msg)
        currentToast?.show()
    }

    protected open fun onKeyboardVisibilityChanged(isVisible: Boolean) {}

    protected fun hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    protected fun showKeyboardAndFocus(view: View) {
        view.requestFocus()
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun Context.hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun setupKeyboardListener() {
        view?.rootView?.viewTreeObserver?.addOnGlobalLayoutListener(keyboardListener)
    }
}