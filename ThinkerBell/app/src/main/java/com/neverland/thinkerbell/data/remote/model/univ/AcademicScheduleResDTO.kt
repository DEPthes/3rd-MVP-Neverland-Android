package com.neverland.thinkerbell.data.remote.model.univ

import com.google.gson.annotations.SerializedName

data class AcademicScheduleResDTO(
    @SerializedName("endDate") val endDate: String,
    @SerializedName("id") val id: Int,
    @SerializedName("marked") val marked: Boolean,
    @SerializedName("startDate") val startDate: String,
    @SerializedName("title") val title: String
)