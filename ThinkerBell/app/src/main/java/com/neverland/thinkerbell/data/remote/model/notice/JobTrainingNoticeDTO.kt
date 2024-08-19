package com.neverland.thinkerbell.data.remote.model.notice
import com.google.gson.annotations.SerializedName


data class JobTrainingNoticeDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("company") val company: String,
    @SerializedName("deadline") val deadline: String,
    @SerializedName("jobName") val jobName: String,
    @SerializedName("major") val major: String,
    @SerializedName("marked") val marked: Boolean,
    @SerializedName("period") val period: String,
    @SerializedName("recrutingNum") val recrutingNum: String,
    @SerializedName("semester") val semester: String,
    @SerializedName("year") val year: String
)