package com.neverland.thinkerbell.presentation.custom

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import com.neverland.thinkerbell.databinding.ToastCustomBinding

object CustomToast {
    fun makeToast(context: Context?, msg: String): Toast {
        val binding = ToastCustomBinding.inflate(LayoutInflater.from(context))

        binding.tvMsg.text = msg

        return Toast(context).apply {
            duration = Toast.LENGTH_SHORT
            view = binding.root
        }
    }
}