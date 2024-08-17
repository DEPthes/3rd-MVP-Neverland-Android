package com.neverland.thinkerbell.data.remote.service

import com.neverland.thinkerbell.data.remote.model.BaseResponse
import com.neverland.thinkerbell.data.remote.model.notice.DormitoryNoticeDTO
import com.neverland.thinkerbell.data.remote.model.notice.JobTrainingNoticeDTO
import com.neverland.thinkerbell.data.remote.model.notice.LibraryNoticeDTO
import com.neverland.thinkerbell.data.remote.model.notice.TeachingNoticeDTO
import com.neverland.thinkerbell.data.remote.model.univ.DeptContactResDTO
import com.neverland.thinkerbell.data.remote.model.univ.DeptUrlResDTO
import retrofit2.Response
import retrofit2.http.GET

interface NoticeService {

    @GET("/api/dormitory/notices")
    suspend fun getDormitoryNotices(): Response<BaseResponse<List<DormitoryNoticeDTO>>>

    @GET("/api/dormitory/entry-notices")
    suspend fun getDormitoryEntryNotices(): Response<BaseResponse<List<DormitoryNoticeDTO>>>

    @GET("/api/job-training")
    suspend fun getJobTrainingNotices(): Response<BaseResponse<List<JobTrainingNoticeDTO>>>

    @GET("/api/library")
    suspend fun getLibraryNotices(): Response<BaseResponse<List<LibraryNoticeDTO>>>

    @GET("/api/teaching")
    suspend fun getTeachingNotices(): Response<BaseResponse<List<TeachingNoticeDTO>>>
}