package com.neverland.thinkerbell.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.databinding.ItemHomeNoticeBinding
import com.neverland.thinkerbell.domain.model.Notice

class HomeNoticeAdapter(private val notices: List<Notice>) :
    RecyclerView.Adapter<HomeNoticeAdapter.NoticeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val binding = ItemHomeNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(notices[position])
    }

    override fun getItemCount(): Int {
        return notices.size
    }

    class NoticeViewHolder(private val binding: ItemHomeNoticeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(notice: Notice) {
            binding.tvNoticeTitle.text = notice.title
            binding.tvNoticeDate.text = notice.date
        }
    }
}

