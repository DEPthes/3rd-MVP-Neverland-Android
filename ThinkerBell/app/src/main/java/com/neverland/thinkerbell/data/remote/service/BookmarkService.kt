package com.neverland.thinkerbell.data.remote.service

import com.neverland.thinkerbell.data.remote.model.BaseResponse
import com.neverland.thinkerbell.data.remote.model.notice.BookmarkNoticeDTO
import com.neverland.thinkerbell.data.remote.model.notice.RecentBookmarkNoticeDTO
import com.neverland.thinkerbell.data.remote.model.user.PostUserInfoReqDTO
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BookmarkService {

    @GET("/api/bookmark")
    suspend fun getNoticeBookmark(
        @Query("ssaid") ssaId: String
    ): Response<BaseResponse<BookmarkNoticeDTO>>

    @GET("/api/bookmark/recent")
    suspend fun getRecentNoticeBookmark(
        @Query("ssaid") ssaId: String
    ): Response<BaseResponse<List<RecentBookmarkNoticeDTO>>>

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