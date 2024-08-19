package com.neverland.thinkerbell.domain.model.notice

data class AllNotices(
    val safetyNotice: List<NoticeItem>?,
    val revisionNotice: List<NoticeItem>?,
    val libraryNotice: List<NoticeItem>?,
    val dormitoryNotice: List<NoticeItem>?,
    val teachingNotice: List<NoticeItem>?,
    val jobTrainingNotice: List<NoticeItem>?,
    val eventNotice: List<NoticeItem>?,
    val studentActsNotice: List<NoticeItem>?,
    val academicNotice: List<NoticeItem>?,
    val careerNotice: List<NoticeItem>?,
    val biddingNotice: List<NoticeItem>?,
    val dormitoryEntryNotice: List<NoticeItem>?,
    val scholarshipNotice: List<NoticeItem>?,
    val normalNotice: List<NoticeItem>?
)