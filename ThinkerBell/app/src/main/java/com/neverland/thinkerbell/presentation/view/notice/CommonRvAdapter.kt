package com.neverland.thinkerbell.presentation.view.notice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.ItemNoticeBinding
import com.neverland.thinkerbell.databinding.ItemNoticeJobBinding
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.model.notice.NoticeItem
import com.neverland.thinkerbell.presentation.view.OnRvItemClickListener

class CommonRvAdapter(private val noticeType: NoticeType) : ListAdapter<NoticeItem, RecyclerView.ViewHolder>(noticeDiffUtil) {

    companion object {
        private const val VIEW_TYPE_COMMON = 1
        private const val VIEW_TYPE_JOB = 2

        private val noticeDiffUtil = object : DiffUtil.ItemCallback<NoticeItem>() {
            override fun areItemsTheSame(oldItem: NoticeItem, newItem: NoticeItem): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: NoticeItem, newItem: NoticeItem): Boolean =
                oldItem == newItem
        }
    }

    inner class NoticeViewHolder(private val binding: ItemNoticeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NoticeItem.CommonNotice) {
            binding.tvNoticeTitle.text = if (data.important) "[중요] ${data.title}" else data.title
            binding.tvNoticeDate.text = data.pubDate
            binding.tbFavorites.isChecked = data.marked

            if(data.important){
                binding.clItemNotice.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.category_bg))
            } else {
                binding.clItemNotice.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.primary2))
            }

            binding.tbFavorites.setOnClickListener {
                bookmarkClickListener.onClick(Pair(data.id, binding.tbFavorites.isChecked))
            }

            binding.root.setOnClickListener {
                rvItemClickListener.onClick(data.url)
            }
        }
    }

    inner class JobNoticeViewHolder(private val binding: ItemNoticeJobBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NoticeItem.JobNotice) {
            binding.tvJobCompany.text = data.company
            binding.tvNoticeStatus.text = "진행중"
            binding.tvJobYear.text = data.year
            binding.tvJobTerm.text = data.semester
            binding.tvJobNumOfPeople.text = data.recruitingNum
            binding.tvJobMajor.text = data.major
            binding.tvJobDate.text = data.deadline
            binding.tvJobPeriod.text = data.period
            binding.tvJobTitle.text = data.jobName
            binding.tbFavorites.isChecked = data.marked

            binding.tbFavorites.setOnClickListener {
                bookmarkClickListener.onClick(Pair(data.id, binding.tbFavorites.isChecked))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(noticeType == NoticeType.JOB_TRAINING_NOTICE){
            VIEW_TYPE_JOB
        } else {
            VIEW_TYPE_COMMON
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_JOB -> JobNoticeViewHolder(
                ItemNoticeJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            VIEW_TYPE_COMMON -> NoticeViewHolder(
                ItemNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is NoticeViewHolder -> holder.bind(getItem(position) as NoticeItem.CommonNotice)
            is JobNoticeViewHolder -> holder.bind(getItem(position) as NoticeItem.JobNotice)
        }
    }

    private lateinit var rvItemClickListener: OnRvItemClickListener<String>
    private lateinit var bookmarkClickListener: OnRvItemClickListener<Pair<Int, Boolean>>

    fun setRvItemClickListener(rvItemClickListener: OnRvItemClickListener<String>){
        this.rvItemClickListener = rvItemClickListener
    }

    fun setBookmarkClickListener(bookmarkClickListener: OnRvItemClickListener<Pair<Int, Boolean>>){
        this.bookmarkClickListener = bookmarkClickListener
    }
}
