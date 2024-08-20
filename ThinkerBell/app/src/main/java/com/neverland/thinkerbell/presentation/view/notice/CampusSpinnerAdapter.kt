package com.neverland.thinkerbell.presentation.view.notice

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.neverland.thinkerbell.databinding.ItemSpinnerCampusBinding

class CampusSpinnerAdapter(
    context: Context,
    @LayoutRes private val resId: Int,
    private val spinnerList: List<String>
) : ArrayAdapter<String>(context, resId, spinnerList) {

    var selectedPosition: Int = 0

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSpinnerCampusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.tvSpinnerItem.text = spinnerList[position]

        binding.tvSpinnerItem.textAlignment = TextView.TEXT_ALIGNMENT_VIEW_START

        return binding.root
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        if (position == selectedPosition) {
            val emptyView = View(context)
            emptyView.layoutParams = ViewGroup.LayoutParams(0, 0)
            return emptyView
        }

        val binding = ItemSpinnerCampusBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.tvSpinnerItem.text = spinnerList[position]
        binding.tvSpinnerItem.textAlignment = TextView.TEXT_ALIGNMENT_CENTER

        return binding.root
    }

    override fun getCount() = spinnerList.size
}
