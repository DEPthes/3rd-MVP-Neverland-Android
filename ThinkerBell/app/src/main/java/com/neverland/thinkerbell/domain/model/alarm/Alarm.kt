package com.neverland.thinkerbell.domain.model.alarm

import com.google.gson.annotations.SerializedName

data class Alarm (
    val id: Int,
    val marked: Boolean,
    val noticeTypeEnglish: String,
    val noticeTypeKorean: String,
    val pubDate: String,
    val title: String,
    val url: String,
    var viewed: Boolean
)