package com.neverland.thinkerbell.data.remote.model.notice

import com.google.gson.annotations.SerializedName

data class SearchNoticeResultDTO(
    @SerializedName("NormalNotice")
    val normalNotices: List<CommonNoticeDTO>?,
    @SerializedName("EventNotice")
    val eventNotices: List<CommonNoticeDTO>?,
    @SerializedName("AcademicNotice")
    val academicNotices: List<CommonNoticeDTO>?,
    @SerializedName("ScholarshipNotices")
    val scholarshipNotices: List<CommonNoticeDTO>?,
    @SerializedName("CareerNotice")
    val careerNotices: List<CommonNoticeDTO>?,
    @SerializedName("StudentActsNotice")
    val studentActsNotices: List<CommonNoticeDTO>?,
    @SerializedName("BiddingNotice")
    val biddingNotices: List<CommonNoticeDTO>?,
    @SerializedName("SafetyNotice")
    val safetyNotices: List<CommonNoticeDTO>?,
    @SerializedName("RevisionNotice")
    val revisionNotices: List<CommonNoticeDTO>?,
    @SerializedName("DormitoryNotice")
    val dormitoryNotices: List<CommonNoticeDTO>?,
    @SerializedName("DormitoryEntryNotice")
    val dormitoryEntryNotices: List<CommonNoticeDTO>?,
    @SerializedName("LibraryNotice")
    val libraryNotices: List<CommonNoticeDTO>?,
    @SerializedName("TeachingNotice")
    val teachingNotices: List<CommonNoticeDTO>?,
    @SerializedName("JobTrainingNotice")
    val jobTrainingNotices: List<JobTrainingNoticeDTO>?,
)