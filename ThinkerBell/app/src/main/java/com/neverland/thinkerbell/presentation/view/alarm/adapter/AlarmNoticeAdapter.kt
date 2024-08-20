package com.neverland.thinkerbell.presentation.view.alarm.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.databinding.ItemNoticeBinding
import com.neverland.thinkerbell.domain.model.alarm.Alarm
import com.neverland.thinkerbell.domain.model.notice.CommonNotice
import com.neverland.thinkerbell.presentation.view.OnRvItemClickListener


class AlarmNoticeAdapter : ListAdapter<Alarm, AlarmNoticeAdapter.NoticeViewHolder>(DiffCallback())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val binding = ItemNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlarmNoticeAdapter.NoticeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NoticeViewHolder(private val binding: ItemNoticeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(notice: Alarm) {
            binding.tvNoticeTitle.text = "[${notice.noticeTypeKorean}] ${notice.title}"
            binding.tvNoticeDate.text = notice.pubDate
            // 좋아요 버튼 설정 (기본적으로 false로 설정)
            binding.tbFavorites.isChecked = notice.marked

            // 좋아요 버튼 클릭 이벤트 설정
            binding.tbFavorites.setOnClickListener {
                bookmarkClickListener.onClick(Pair(notice, binding.tbFavorites.isChecked))
            }

            binding.root.setOnClickListener{
                if (!notice.viewed) {
                    notice.viewed = true
                    notifyItemChanged(adapterPosition)
                }
                rvItemClickListener.onClick(Pair(notice.id, notice.url))
            }
            // 읽음 여부에 따라 배경색 설정
            if (notice.viewed) {
                binding.root.setBackgroundColor(Color.parseColor("#E4E9EF")) // 읽은 공지 배경색
            } else {
                binding.root.setBackgroundColor(Color.WHITE) // 기본 배경색
            }
        }

    }

    private lateinit var rvItemClickListener: OnRvItemClickListener<Pair<Int, String>>

    fun setRvItemClickListener(rvItemClickListener: OnRvItemClickListener<Pair<Int, String>>) {
        this.rvItemClickListener = rvItemClickListener
    }

    private lateinit var bookmarkClickListener: OnRvItemClickListener<Pair<Alarm, Boolean>>

    fun setBookmarkClickListener(bookmarkClickListener: OnRvItemClickListener<Pair<Alarm, Boolean>>) {
        this.bookmarkClickListener = bookmarkClickListener
    }

    class DiffCallback : DiffUtil.ItemCallback<Alarm>() {
        override fun areItemsTheSame(oldItem: Alarm, newItem: Alarm): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Alarm, newItem: Alarm): Boolean {
            return oldItem == newItem
        }
    }
}