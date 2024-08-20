package com.neverland.thinkerbell.data.remote.service

import com.neverland.thinkerbell.data.remote.model.BaseResponse
import com.neverland.thinkerbell.data.remote.model.PageableResponse
import com.neverland.thinkerbell.data.remote.model.notice.CommonNoticeDTO
import com.neverland.thinkerbell.data.remote.model.notice.JobTrainingNoticeDTO
import com.neverland.thinkerbell.data.remote.model.notice.SearchNoticeResultDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NoticeService {

    @GET("/api/notices/search")
    suspend fun searchNotices(
        @Query("keyword") keyword: String,
        @Query("ssaid") ssaId: String
    ): Response<BaseResponse<SearchNoticeResultDTO>>

    @GET("/api/notices/recent")
    suspend fun getRecentNotices(
        @Query("ssaid") ssaId: String
    ): Response<BaseResponse<SearchNoticeResultDTO>>

    @GET("/api/dormitory/notices")
    suspend fun getDormitoryNotices(
        @Query("page") page: Int,
        @Query("ssaid") ssaId: String,
        @Query("campus") campus: String
    ): Response<BaseResponse<PageableResponse<CommonNoticeDTO>>>

    @GET("/api/dormitory/entry-notices")
    suspend fun getDormitoryEntryNotices(
        @Query("page") page: Int,
        @Query("ssaid") ssaId: String,
        @Query("campus") campus: String
    ): Response<BaseResponse<PageableResponse<CommonNoticeDTO>>>

    @GET("/api/job-training")
    suspend fun getJobTrainingNotices(
        @Query("page") page: Int,
        @Query("ssaid") ssaId: String
    ): Response<BaseResponse<PageableResponse<JobTrainingNoticeDTO>>>

    @GET("/api/library")
    suspend fun getLibraryNotices(
        @Query("page") page: Int,
        @Query("ssaid") ssaId: String,
        @Query("campus") campus: String
    ): Response<BaseResponse<PageableResponse<CommonNoticeDTO>>>

    // 학교 공지
    @GET("/api/teaching")
    suspend fun getTeachingNotices(
        @Query("page") page: Int,
        @Query("ssaid") ssaId: String
    ): Response<BaseResponse<PageableResponse<CommonNoticeDTO>>>

    @GET("/api/academic")
    suspend fun getAcademicNotices(
        @Query("page") page: Int,
        @Query("ssaid") ssaId: String
    ): Response<BaseResponse<PageableResponse<CommonNoticeDTO>>>

    @GET("/api/event")
    suspend fun getEventNotices(
        @Query("page") page: Int,
        @Query("ssaid") ssaId: String
    ): Response<BaseResponse<PageableResponse<CommonNoticeDTO>>>

    @GET("/api/normal")
    suspend fun getNormalNotices(
        @Query("page") page: Int,
        @Query("ssaid") ssaId: String
    ): Response<BaseResponse<PageableResponse<CommonNoticeDTO>>>

    @GET("/api/revision")
    suspend fun getRevisionNotices(
        @Query("page") page: Int,
        @Query("ssaid") ssaId: String
    ): Response<BaseResponse<PageableResponse<CommonNoticeDTO>>>

    @GET("/api/safety")
    suspend fun getSafetyNotices(
        @Query("page") page: Int,
        @Query("ssaid") ssaId: String
    ): Response<BaseResponse<PageableResponse<CommonNoticeDTO>>>

    @GET("/api/scholarship")
    suspend fun getScholarship(
        @Query("page") page: Int,
        @Query("ssaid") ssaId: String
    ): Response<BaseResponse<PageableResponse<CommonNoticeDTO>>>

    @GET("/api/student-acts")
    suspend fun getStudentActsNotices(
        @Query("page") page: Int,
        @Query("ssaid") ssaId: String
    ): Response<BaseResponse<PageableResponse<CommonNoticeDTO>>>

    @GET("/api/career")
    suspend fun getCareerNotices(
        @Query("page") page: Int,
        @Query("ssaid") ssaId: String
    ): Response<BaseResponse<PageableResponse<CommonNoticeDTO>>>

    @GET("/api/bidding")
    suspend fun getBiddingNotices(
        @Query("page") page: Int,
        @Query("ssaid") ssaId: String
    ): Response<BaseResponse<PageableResponse<CommonNoticeDTO>>>
}