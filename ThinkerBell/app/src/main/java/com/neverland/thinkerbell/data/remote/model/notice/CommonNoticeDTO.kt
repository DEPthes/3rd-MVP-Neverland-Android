package com.neverland.thinkerbell.data.remote.model.notice

import com.google.gson.annotations.SerializedName

data class CommonNoticeDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("marked") val marked: Boolean,
    @SerializedName("pubDate") val pubDate: String,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String,
    @SerializedName("important") val important: Boolean?,
    @SerializedName("campus") val campus: String?
)