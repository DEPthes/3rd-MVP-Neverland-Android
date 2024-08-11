package com.neverland.thinkerbell.data.remote.model.univ
import com.google.gson.annotations.SerializedName


data class DeptContactResDTO(
    @SerializedName("campus") val campus: String,
    @SerializedName("college") val college: String,
    @SerializedName("contact") val contact: String,
    @SerializedName("major") val major: String
)