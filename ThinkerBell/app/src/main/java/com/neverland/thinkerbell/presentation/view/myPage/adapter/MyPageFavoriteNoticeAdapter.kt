package com.neverland.thinkerbell.presentation.view.myPage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.databinding.ItemMyNoticeBinding
import com.neverland.thinkerbell.domain.model.notice.CommonNotice

class MyPageFavoriteNoticeAdapter(private val notices: List<CommonNotice>) :
    RecyclerView.Adapter<MyPageFavoriteNoticeAdapter.MyPageFavoriteNoticeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageFavoriteNoticeViewHolder {
        val binding = ItemMyNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyPageFavoriteNoticeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPageFavoriteNoticeViewHolder, position: Int) {
        holder.bind(notices[position])
    }

    override fun getItemCount(): Int {
        return notices.size
    }

    class MyPageFavoriteNoticeViewHolder(private val binding: ItemMyNoticeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(notice: CommonNotice) {
            binding.tvNoticeTitle.text = notice.title
            binding.tvNoticeDate.text = notice.date
        }
    }
}
