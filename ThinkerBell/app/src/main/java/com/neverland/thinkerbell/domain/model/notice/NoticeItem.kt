package com.neverland.thinkerbell.domain.model.notice

import com.google.gson.annotations.SerializedName

sealed class NoticeItem {
    data class CommonNotice(
        val id: Int,
        val title: String,
        val pubDate: String,
        val url: String,
        val campus: String? = null,
        val important: Boolean = false,
    ): NoticeItem()

    data class JobNotice(
        val company: String,
        val year: String,
        val semester: String,
        val recruitingNum: String,
        val major: String,
        val deadline: String,
        val period: String,
        val jobName: String
    ) : NoticeItem()


}