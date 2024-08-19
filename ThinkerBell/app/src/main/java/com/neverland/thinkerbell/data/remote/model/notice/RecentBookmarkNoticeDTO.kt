package com.neverland.thinkerbell.data.remote.model.notice

import com.google.gson.annotations.SerializedName

data class RecentBookmarkNoticeDTO (
    @SerializedName("category") val category: String,
    @SerializedName("title") val title: String,
    @SerializedName("pubDate") val pubDate: String,
    @SerializedName("url") val url: String
)