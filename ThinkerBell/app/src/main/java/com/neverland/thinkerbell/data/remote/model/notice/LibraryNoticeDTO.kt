package com.neverland.thinkerbell.data.remote.model.notice
import com.google.gson.annotations.SerializedName


data class LibraryNoticeDTO(
    @SerializedName("campus") val campus: String,
    @SerializedName("id") val id: Int,
    @SerializedName("pubDate") val pubDate: String,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String
)