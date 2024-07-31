package com.neverland.thinkerbell.presentation.view.notice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.databinding.ItemNoticeBinding
import com.neverland.thinkerbell.domain.model.notice.CommonNotice

class CommonRvAdapter: ListAdapter<CommonNotice, RecyclerView.ViewHolder>(noticeDiffUtil) {
    companion object {
        private val noticeDiffUtil = object : DiffUtil.ItemCallback<CommonNotice>() {
            override fun areItemsTheSame(oldItem: CommonNotice, newItem: CommonNotice): Boolean =
                oldItem === newItem

            override fun areContentsTheSame(oldItem: CommonNotice, newItem: CommonNotice): Boolean =
                oldItem == newItem
        }
    }

    inner class NoticeViewHolder(private val binding: ItemNoticeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: CommonNotice){
            binding.tvNoticeTitle.text = data.title
            binding.tvNoticeDate.text = data.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NoticeViewHolder(ItemNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return (holder as NoticeViewHolder).bind(getItem(position))
    }
}