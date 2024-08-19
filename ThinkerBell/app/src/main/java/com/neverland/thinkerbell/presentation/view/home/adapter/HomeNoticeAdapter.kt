package com.neverland.thinkerbell.presentation.view.home.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.databinding.ItemHomeNoticeBinding
import com.neverland.thinkerbell.domain.model.notice.NoticeItem

class HomeNoticeAdapter(private val notices: List<NoticeItem.CommonNotice>) :
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
        fun bind(notice: NoticeItem.CommonNotice) {
            binding.tvNoticeTitle.text = notice.title
            binding.tvNoticeDate.text = notice.pubDate

            binding.root.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(notice.url))
                startActivity(binding.root.context, intent, null)
            }
        }
    }
}

