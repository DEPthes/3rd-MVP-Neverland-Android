package com.neverland.thinkerbell.data.remote.service

import com.neverland.thinkerbell.data.remote.model.BaseResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Query

interface BookmarkService {

    @POST("/api/bookmark")
    suspend fun postNoticeBookmark(
        @Query("category") category: String,
        @Query("notice-id") noticeId: Int,
        @Query("ssaid") ssaId: String
    ): Response<BaseResponse<ResponseBody>>

    @DELETE("/api/bookmark")
    suspend fun deleteNoticeBookmark(
        @Query("category") category: String,
        @Query("notice-id") noticeId: Int,
        @Query("ssaid") ssaId: String
    ): Response<BaseResponse<ResponseBody>>
}