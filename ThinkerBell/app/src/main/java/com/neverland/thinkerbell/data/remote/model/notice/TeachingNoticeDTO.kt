package com.neverland.thinkerbell.data.remote.model.notice
import com.google.gson.annotations.SerializedName


data class TeachingNoticeDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("important") val important: Boolean,
    @SerializedName("pubDate") val pubDate: String,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String
)