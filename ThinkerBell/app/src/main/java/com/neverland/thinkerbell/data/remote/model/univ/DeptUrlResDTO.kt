package com.neverland.thinkerbell.data.remote.model.univ
import com.google.gson.annotations.SerializedName


data class DeptUrlResDTO(
    @SerializedName("college") val college: String,
    @SerializedName("school") val school: String,
    @SerializedName("url") val url: String
)