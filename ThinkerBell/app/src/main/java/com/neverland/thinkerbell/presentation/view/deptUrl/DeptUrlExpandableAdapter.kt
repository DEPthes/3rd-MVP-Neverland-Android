package com.neverland.thinkerbell.presentation.view.deptUrl

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.ItemDeptUrlBinding
import com.neverland.thinkerbell.databinding.ItemGroupBinding
import com.neverland.thinkerbell.domain.model.group.Group
import com.neverland.thinkerbell.domain.model.univ.DeptUrl
import com.neverland.thinkerbell.presentation.view.OnRvItemClickListener

@SuppressLint("NotifyDataSetChanged")
class DeptUrlExpandableAdapter(private val groups: List<Group<DeptUrl>>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_GROUP = 0
        private const val TYPE_ITEM = 1
    }

    override fun getItemViewType(position: Int): Int {
        var currentPosition = 0
        groups.forEach { group ->
            if (position == currentPosition) {
                return TYPE_GROUP
            }
            currentPosition++
            if (group.isExpanded) {
                group.items!!.forEach { _ ->
                    if (position == currentPosition) {
                        return TYPE_ITEM
                    }
                    currentPosition++
                }
            }
        }
        throw IllegalArgumentException("Invalid position")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_GROUP -> {
                val binding = ItemGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                GroupViewHolder(binding)
            }
            TYPE_ITEM -> {
                val binding = ItemDeptUrlBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var currentPosition = 0
        groups.forEach { group ->
            if (position == currentPosition) {
                (holder as GroupViewHolder).bind(group)
                return
            }
            currentPosition++
            if (group.isExpanded) {
                group.items!!.forEach { item ->
                    if (position == currentPosition) {
                        (holder as ItemViewHolder).bind(item)
                        return
                    }
                    currentPosition++
                }
            }
        }
    }

    override fun getItemCount(): Int {
        var count = 0
        groups.forEach { group ->
            count++
            if (group.isExpanded) {
                count += group.items!!.size
            }
        }
        return count
    }

    inner class GroupViewHolder(private val binding: ItemGroupBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(group: Group<DeptUrl>) {
            binding.tvGroupTitle.text = group.name

            if (group.isExpanded) {
                binding.ibGroupExpand.setImageResource(R.drawable.ic_direaction_up_1)
            } else {
                binding.ibGroupExpand.setImageResource(R.drawable.ic_direaction_down_1)
            }

            binding.ibGroupExpand.setOnClickListener {
                group.isExpanded = !group.isExpanded
                notifyDataSetChanged()
            }

            itemView.setOnClickListener {
                group.isExpanded = !group.isExpanded
                notifyDataSetChanged()
            }
        }
    }

    inner class ItemViewHolder(private val binding: ItemDeptUrlBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DeptUrl) {
            binding.tvDeptUrlTitle.text = item.college

            itemView.setOnClickListener {
                rvItemClickListener.onClick(item.url)
            }
            binding.ibLoadPage.setOnClickListener {
                rvItemClickListener.onClick(item.url)
            }
        }
    }

    private lateinit var rvItemClickListener: OnRvItemClickListener<String>

    fun setOnRvItemClickListener(rvItemClickListener: OnRvItemClickListener<String>) {
        this.rvItemClickListener = rvItemClickListener
    }
}

