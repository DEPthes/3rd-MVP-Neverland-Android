package com.neverland.thinkerbell.data.remote.model.notice

import com.google.gson.annotations.SerializedName

data class BookmarkNoticeDTO(
    @SerializedName("safetyNotice") val safetyNotice: List<CommonNoticeDTO>?,
    @SerializedName("revisionNotice") val revisionNotice: List<CommonNoticeDTO>?,
    @SerializedName("libraryNotice") val libraryNotice: List<CommonNoticeDTO>?,
    @SerializedName("dormitoryNotice") val dormitoryNotice: List<CommonNoticeDTO>?,
    @SerializedName("teachingNotice") val teachingNotice: List<CommonNoticeDTO>?,
    @SerializedName("jobTrainingNotice") val jobTrainingNotice: List<JobTrainingNoticeDTO>?,
    @SerializedName("eventNotice") val eventNotice: List<CommonNoticeDTO>?,
    @SerializedName("studentActsNotice") val studentActsNotice: List<CommonNoticeDTO>?,
    @SerializedName("academicNotice") val academicNotice: List<CommonNoticeDTO>?,
    @SerializedName("careerNotice") val careerNotice: List<CommonNoticeDTO>?,
    @SerializedName("biddingNotice") val biddingNotice: List<CommonNoticeDTO>?,
    @SerializedName("dormitoryEntryNotice") val dormitoryEntryNotice: List<CommonNoticeDTO>?,
    @SerializedName("scholarshipNotice") val scholarshipNotice: List<CommonNoticeDTO>?
)
