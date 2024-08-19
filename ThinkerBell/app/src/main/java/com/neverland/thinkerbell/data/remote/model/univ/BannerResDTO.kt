package com.neverland.thinkerbell.data.remote.model.univ

import com.google.gson.annotations.SerializedName

data class BannerResDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("s3Url") val s3Url: String,
    @SerializedName("noticeUrl") val noticeUrl: String
)
