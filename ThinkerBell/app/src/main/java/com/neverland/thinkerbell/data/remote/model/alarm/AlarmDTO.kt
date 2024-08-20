package com.neverland.thinkerbell.data.remote.model.alarm

import com.google.gson.annotations.SerializedName

data class AlarmDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("marked")
    val marked: Boolean,
    @SerializedName("noticeType_english")
    val noticeTypeEnglish: String,
    @SerializedName("noticeType_korean")
    val noticeTypeKorean: String,
    @SerializedName("pubDate")
    val pubDate: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("viewed")
    val viewed: Boolean
)