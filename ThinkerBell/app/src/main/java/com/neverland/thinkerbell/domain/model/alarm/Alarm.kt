package com.neverland.thinkerbell.domain.model.alarm

data class Alarm (
    val id: Int,
    val title: String,
    val noticeType: String,
    var isViewed: Boolean,
    val pubDate: String,
    val url: String,
    val marked: Boolean
)