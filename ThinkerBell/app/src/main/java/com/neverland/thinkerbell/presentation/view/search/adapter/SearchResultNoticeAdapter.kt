package com.neverland.thinkerbell.presentation.view.notice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.databinding.ItemNoticeBinding
import com.neverland.thinkerbell.domain.model.notice.CommonNotice

class SearchResultNoticeAdapter : ListAdapter<CommonNotice, SearchResultNoticeAdapter.NoticeViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val binding = ItemNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NoticeViewHolder(private val binding: ItemNoticeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(notice: CommonNotice) {
            binding.tvNoticeTitle.text = notice.title
            binding.tvNoticeDate.text = notice.date
            // 좋아요 버튼 설정 (기본적으로 false로 설정)
            binding.tbFavorites.isChecked = notice.isImportant ?: false

            // 좋아요 버튼 클릭 이벤트 설정
            binding.tbFavorites.setOnCheckedChangeListener { _, isChecked ->
                // 좋아요 상태 변경 처리 (예: 데이터 저장, UI 업데이트 등)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CommonNotice>() {
        override fun areItemsTheSame(oldItem: CommonNotice, newItem: CommonNotice): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: CommonNotice, newItem: CommonNotice): Boolean {
            return oldItem == newItem
        }
    }
}
