package com.neverland.thinkerbell.presentation.view.myPage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.databinding.ItemMyPageKeywordBinding
import com.neverland.thinkerbell.domain.model.keyword.Keyword

class MyPageKeywordAdapter(
    private val keywords: List<Keyword>
) : RecyclerView.Adapter<MyPageKeywordAdapter.KeywordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordViewHolder {
        val binding = ItemMyPageKeywordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KeywordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KeywordViewHolder, position: Int) {
        holder.bind(keywords[position])
    }

    override fun getItemCount(): Int = keywords.size

    class KeywordViewHolder(private val binding: ItemMyPageKeywordBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(keyword: Keyword) {
            binding.tvKeyword.text = keyword.keyword
        }
    }
}
