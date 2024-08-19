package com.neverland.thinkerbell.domain.model.notice

data class RecentNotices(
    val academicNotices: List<NoticeItem.CommonNotice>,
    val careerNotices: List<NoticeItem.CommonNotice>,
    val scholarshipNotices: List<NoticeItem.CommonNotice>,
    val eventNotices: List<NoticeItem.CommonNotice>,
    val normalNotices: List<NoticeItem.CommonNotice>
)
