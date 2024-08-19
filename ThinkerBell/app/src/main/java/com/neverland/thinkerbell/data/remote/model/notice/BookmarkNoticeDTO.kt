package com.neverland.thinkerbell.data.remote.model.notice

import com.google.gson.annotations.SerializedName

data class BookmarkNoticeDTO(
    @SerializedName("SafetyNotice") val safetyNotice: List<CommonNoticeDTO>?,
    @SerializedName("RevisionNotice") val revisionNotice: List<CommonNoticeDTO>?,
    @SerializedName("LibraryNotice") val libraryNotice: List<CommonNoticeDTO>?,
    @SerializedName("DormitoryNotice") val dormitoryNotice: List<CommonNoticeDTO>?,
    @SerializedName("TeachingNotice") val teachingNotice: List<CommonNoticeDTO>?,
    @SerializedName("JobTrainingNotice") val jobTrainingNotice: List<JobTrainingNoticeDTO>?,
    @SerializedName("EventNotice") val eventNotice: List<CommonNoticeDTO>?,
    @SerializedName("StudentActsNotice") val studentActsNotice: List<CommonNoticeDTO>?,
    @SerializedName("AcademicNotice") val academicNotice: List<CommonNoticeDTO>?,
    @SerializedName("CareerNotice") val careerNotice: List<CommonNoticeDTO>?,
    @SerializedName("BiddingNotice") val biddingNotice: List<CommonNoticeDTO>?,
    @SerializedName("DormitoryEntryNotice") val dormitoryEntryNotice: List<CommonNoticeDTO>?,
    @SerializedName("ScholarshipNotice") val scholarshipNotice: List<CommonNoticeDTO>?,
    @SerializedName("NormalNotice") val normalNotice: List<CommonNoticeDTO>?,
)
