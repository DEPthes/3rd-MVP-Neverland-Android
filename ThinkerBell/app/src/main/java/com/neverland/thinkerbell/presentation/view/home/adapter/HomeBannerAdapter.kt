package com.neverland.thinkerbell.presentation.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neverland.thinkerbell.databinding.ItemHomeBannerBinding

class HomeBannerAdapter(private val banners: List<Int>) :
    RecyclerView.Adapter<HomeBannerAdapter.BannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding = ItemHomeBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(banners[position])
    }

    override fun getItemCount(): Int {
        return banners.size
    }

    class BannerViewHolder(private val binding: ItemHomeBannerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageResId: Int) {
            Glide.with(binding.imageView)
                .load(imageResId)
                .into(binding.imageView)
        }
    }
}
