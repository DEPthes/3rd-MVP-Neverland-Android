package com.neverland.thinkerbell.presentation.view.home.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neverland.thinkerbell.databinding.ItemHomeBannerBinding
import com.neverland.thinkerbell.domain.model.univ.Banner

class HomeBannerAdapter(private val banners: List<Banner>) :
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
        fun bind(item: Banner) {

            binding.root.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.noticeUrl))
                ContextCompat.startActivity(binding.root.context, intent, null)
            }

            Glide.with(binding.imageView)
                .load(item.s3Url)
                .into(binding.imageView)
        }
    }
}
