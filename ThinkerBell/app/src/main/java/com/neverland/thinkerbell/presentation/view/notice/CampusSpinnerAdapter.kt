package com.neverland.thinkerbell.presentation.view.notice

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.neverland.thinkerbell.core.utils.LoggerUtil
import com.neverland.thinkerbell.databinding.ItemSpinnerCampusBinding

@SuppressLint("ViewHolder")
class CampusSpinnerAdapter(
    context: Context,
    private val spinner: Spinner,
    @LayoutRes private val resId: Int,
    private val spinnerList: List<String>
): ArrayAdapter<String>(context, resId, spinnerList) {

    private var selectedPosition: Int = 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerCampusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.tvSpinnerItem.text = spinnerList[position]

        binding.tvSpinnerItem.textAlignment = TextView.TEXT_ALIGNMENT_VIEW_START

        binding.tvSpinnerItem.setOnClickListener {
            LoggerUtil.i("click")
        }

        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerCampusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.tvSpinnerItem.text = spinnerList[position]

        if (position == selectedPosition) {
            binding.root.visibility = View.GONE
            binding.root.layoutParams = ViewGroup.LayoutParams(0, 1)
        } else {
            binding.tvSpinnerItem.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        }

        return binding.root
    }

    override fun getCount() = spinnerList.size
}
