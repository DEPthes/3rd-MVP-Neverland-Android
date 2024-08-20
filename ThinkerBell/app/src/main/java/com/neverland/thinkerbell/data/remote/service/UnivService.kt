package com.neverland.thinkerbell.data.remote.service

import com.neverland.thinkerbell.data.remote.model.BaseResponse
import com.neverland.thinkerbell.data.remote.model.univ.AcademicScheduleResDTO
import com.neverland.thinkerbell.data.remote.model.univ.BannerResDTO
import com.neverland.thinkerbell.data.remote.model.univ.DeptContactResDTO
import com.neverland.thinkerbell.data.remote.model.univ.DeptUrlResDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UnivService {
    @GET("/api/dept-url")
    suspend fun getDeptUrl(): Response<BaseResponse<List<DeptUrlResDTO>>>

    @GET("/api/dept-contact")
    suspend fun getDeptContact(): Response<BaseResponse<List<DeptContactResDTO>>>

    @GET("/api/academic-schedule/monthly")
    suspend fun getMonthlyAcademicSchedule(
        @Query("month") month: Int,
        @Query("ssaid") ssaId: String
    ): Response<BaseResponse<List<AcademicScheduleResDTO>>>

    @GET("/api/banners")
    suspend fun getBanner(): Response<BaseResponse<List<BannerResDTO>>>
}