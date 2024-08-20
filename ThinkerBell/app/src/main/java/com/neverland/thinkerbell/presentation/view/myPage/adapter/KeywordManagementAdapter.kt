package com.neverland.thinkerbell.presentation.view.myPage.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.databinding.ItemKeywordManagementBinding
import com.neverland.thinkerbell.domain.model.keyword.Keyword
import com.neverland.thinkerbell.presentation.view.OnRvItemClickListener

class KeywordManagementAdapter(
    private var keywords: List<Keyword>
) : RecyclerView.Adapter<KeywordManagementAdapter.KeywordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordViewHolder {
        val binding = ItemKeywordManagementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KeywordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KeywordViewHolder, position: Int) {
        holder.bind(keywords[position])
    }

    override fun getItemCount(): Int = keywords.size

    inner class KeywordViewHolder(private val binding: ItemKeywordManagementBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(keyword: Keyword) {
            binding.tvKeyword.text = keyword.keyword
            binding.ibDelete.setOnClickListener {
               rvItemClickListener.onClick(keyword.keyword)
            }
        }
    }

    private lateinit var rvItemClickListener: OnRvItemClickListener<String>

    fun setOnRvItemClickListener(rvItemClickListener: OnRvItemClickListener<String>) {
        this.rvItemClickListener = rvItemClickListener
    }

    fun deleteKeyword(keyword: String) {
        val temp = keywords.toMutableList()
        temp.remove(Keyword(keyword))
        keywords = temp
        notifyDataSetChanged()
    }
}
