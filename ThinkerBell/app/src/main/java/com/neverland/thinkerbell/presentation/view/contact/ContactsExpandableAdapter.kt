package com.neverland.thinkerbell.presentation.view.contact

import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.neverland.thinkerbell.R
import com.neverland.thinkerbell.databinding.ItemContactBinding
import com.neverland.thinkerbell.databinding.ItemGroupBinding
import com.neverland.thinkerbell.databinding.ItemSubGroupBinding
import com.neverland.thinkerbell.domain.model.group.ContactItem
import com.neverland.thinkerbell.domain.model.group.Group
import com.neverland.thinkerbell.domain.model.group.SubGroup
import com.neverland.thinkerbell.presentation.view.OnRvItemClickListener

class ContactsExpandableAdapter(private val groups: List<Group<ContactItem>>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_GROUP = 0
        private const val TYPE_SUBGROUP = 1
        private const val TYPE_ITEM = 2
    }

    override fun getItemViewType(position: Int): Int {
        var currentPosition = 0
        groups.forEach { group ->
            if (position == currentPosition) {
                return TYPE_GROUP
            }
            currentPosition++
            if (group.isExpanded) {
                group.subGroups.forEach { subgroup ->
                    if (position == currentPosition) {
                        return TYPE_SUBGROUP
                    }
                    currentPosition++
                    if (subgroup.isExpanded) {
                        subgroup.items.forEach { _ ->
                            if (position == currentPosition) {
                                return TYPE_ITEM
                            }
                            currentPosition++
                        }
                    }
                }
            }
        }
        throw IllegalArgumentException("Invalid position")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_GROUP -> {
                val binding = ItemGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                GroupViewHolder(binding)
            }
            TYPE_SUBGROUP -> {
                val binding = ItemSubGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                SubgroupViewHolder(binding)
            }
            TYPE_ITEM -> {
                val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var currentPosition = 0
        groups.forEach { group ->
            if (position == currentPosition) {
                (holder as GroupViewHolder).bind(group)
                holder.itemView.setOnClickListener {
                    group.isExpanded = !group.isExpanded
                    notifyDataSetChanged()
                }
                return
            }
            currentPosition++
            if (group.isExpanded) {
                group.subGroups.forEach { subgroup ->
                    if (position == currentPosition) {
                        (holder as SubgroupViewHolder).bind(subgroup)
                        holder.itemView.setOnClickListener {
                            subgroup.isExpanded = !subgroup.isExpanded
                            notifyDataSetChanged()
                        }
                        return
                    }
                    currentPosition++
                    if (subgroup.isExpanded) {
                        subgroup.items.forEach { item ->
                            if (position == currentPosition) {
                                (holder as ItemViewHolder).bind(item)
                                return
                            }
                            currentPosition++
                        }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        var count = 0
        groups.forEach { group ->
            count++
            if (group.isExpanded) {
                group.subGroups.forEach { subgroup ->
                    count++
                    if (subgroup.isExpanded) {
                        count += subgroup.items.size
                    }
                }
            }
        }
        return count
    }

    inner class GroupViewHolder(private val binding: ItemGroupBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(group: Group<ContactItem>) {
            binding.tvGroupTitle.text = group.name

            if(group.isExpanded){
                binding.ibGroupExpand.setImageResource(R.drawable.ic_direaction_up_1)
            } else {
                binding.ibGroupExpand.setImageResource(R.drawable.ic_direaction_down_1)
            }
        }
    }

    inner class SubgroupViewHolder(private val binding: ItemSubGroupBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(subgroup: SubGroup<ContactItem>) {
            binding.tvSubGroupTitle.text = subgroup.name

            if(subgroup.isExpanded){
                binding.clSubGroup.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.red_gray_100))
                binding.ibSubGroupExpand.setImageResource(R.drawable.ic_direaction_up_1)
            } else {
                binding.clSubGroup.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.primary2))
                binding.ibSubGroupExpand.setImageResource(R.drawable.ic_direaction_down_1)
            }
        }
    }

    inner class ItemViewHolder(private val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ContactItem) {
            binding.tvContactTitle.text = item.name

            val underlineContact = SpannableString(item.contact)
            underlineContact.setSpan(
                UnderlineSpan(), 0, item.contact.length, 0
            )

            binding.tvContact.text = underlineContact

            itemView.setOnClickListener {
                rvItemClickListener.onClick(item)
            }
        }
    }

    private lateinit var rvItemClickListener: OnRvItemClickListener<ContactItem>

    fun setOnRvItemClickListener(rvItemClickListener: OnRvItemClickListener<ContactItem>){
        this.rvItemClickListener = rvItemClickListener
    }
}
