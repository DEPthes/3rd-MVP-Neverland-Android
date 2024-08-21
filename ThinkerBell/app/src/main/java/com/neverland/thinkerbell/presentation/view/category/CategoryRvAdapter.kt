package com.neverland.thinkerbell.presentation.view.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.databinding.ItemCategoryBinding
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.presentation.view.OnRvItemClickListener
import java.util.Collections

class CategoryRvAdapter: ListAdapter<NoticeType, RecyclerView.ViewHolder>(categoryDiffUtil) {
    companion object {
        private val categoryDiffUtil = object : DiffUtil.ItemCallback<NoticeType>() {
            override fun areItemsTheSame(oldItem: NoticeType, newItem: NoticeType): Boolean =
                oldItem === newItem

            override fun areContentsTheSame(oldItem: NoticeType, newItem: NoticeType): Boolean =
                oldItem == newItem
        }
    }

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: NoticeType){
            binding.tvCategory.text = data.koName
            itemView.setOnClickListener { rvItemClickListener.onClick(data) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CategoryViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return (holder as CategoryViewHolder).bind(getItem(position))
    }

    fun moveItem(fromPosition: Int, toPosition: Int) {
        val newList = currentList.toMutableList()
        Collections.swap(newList, fromPosition, toPosition)
        submitList(newList)
        listOrderChangeListener.onClick(newList)
    }

    private lateinit var rvItemClickListener: OnRvItemClickListener<NoticeType>

    fun setRvItemClickListener(rvItemClickListener: OnRvItemClickListener<NoticeType>){
        this.rvItemClickListener = rvItemClickListener
    }

    private lateinit var listOrderChangeListener: OnRvItemClickListener<List<NoticeType>>

    fun setListOrderChangeListener(listOrderChangeListener: OnRvItemClickListener<List<NoticeType>>){
        this.listOrderChangeListener = listOrderChangeListener
    }
}