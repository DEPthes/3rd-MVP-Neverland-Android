package com.neverland.thinkerbell.data.remote.model.alarm

import com.google.gson.annotations.SerializedName

data class AlarmDTO (
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("noticeType") val noticeType: String,
    @SerializedName("viewed") val isViewed: Boolean,
    @SerializedName("pubDate") val pubDate: String,
    @SerializedName("url") val url: String,
    @SerializedName("marked") val marked: Boolean
)