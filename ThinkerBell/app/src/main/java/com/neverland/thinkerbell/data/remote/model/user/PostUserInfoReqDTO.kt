package com.neverland.thinkerbell.data.remote.model.user
import com.google.gson.annotations.SerializedName


data class PostUserInfoReqDTO(
    @SerializedName("deviceToken") val deviceToken: String,
    @SerializedName("ssaid") val ssaId: String
)