package com.neverland.thinkerbell.data.remote.model.univ

import com.google.gson.annotations.SerializedName

data class AcademicScheduleResDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("univId") val univId: Int,
    @SerializedName("startDate") val startDate: String,
    @SerializedName("endDate") val endDate: String
)
