package com.neverland.thinkerbell.presentation.view.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.databinding.ItemSearchRecentBinding

class SearchRecentWordAdapter(private val deleteClickListener: (String) -> Unit,
                              private val wordClickListener: (String) -> Unit
): ListAdapter<String, SearchRecentWordAdapter.RecentSearchWordViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchWordViewHolder {
        val binding = ItemSearchRecentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentSearchWordViewHolder(binding, deleteClickListener, wordClickListener)
    }

    override fun onBindViewHolder(holder: RecentSearchWordViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class RecentSearchWordViewHolder(
        private val binding: ItemSearchRecentBinding,
        private val deleteClickListener: (String) -> Unit,
        private val wordClickListener: (String) -> Unit
        ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(word: String) {
            binding.tvRecentSearchWord.text = word
            binding.ibDelete.setOnClickListener {
                deleteClickListener(word)
            }
            binding.tvRecentSearchWord.setOnClickListener {
                wordClickListener(word)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}
