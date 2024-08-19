package com.neverland.thinkerbell.presentation.view.alarm.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.databinding.ItemNoticeBinding
import com.neverland.thinkerbell.domain.model.notice.CommonNotice


class AlarmNoticeAdapter : ListAdapter<CommonNotice, AlarmNoticeAdapter.NoticeViewHolder>(DiffCallback())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val binding = ItemNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlarmNoticeAdapter.NoticeViewHolder, position: Int) {
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

            // 읽음 여부에 따라 배경색 설정
            if (isNoticeRead(notice)) {
                binding.root.setBackgroundColor(Color.parseColor("#E4E9EF")) // 읽은 공지 배경색
            } else {
                binding.root.setBackgroundColor(Color.WHITE) // 기본 배경색
            }
        }

        // 공지가 읽혔는지 여부를 확인하는 함수 (서버 연동 시 구현)
        private fun isNoticeRead(notice: CommonNotice): Boolean {
            // 서버 연동 또는 로컬 데이터 확인을 통해 읽음 여부를 판단하는 로직
            // TODO: 서버 연동 시 구현
            return false
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