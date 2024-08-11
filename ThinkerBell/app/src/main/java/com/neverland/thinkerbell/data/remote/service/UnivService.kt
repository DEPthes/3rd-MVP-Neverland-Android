package com.neverland.thinkerbell.data.remote.service

import com.neverland.thinkerbell.data.remote.model.BaseResponse
import com.neverland.thinkerbell.data.remote.model.univ.DeptContactResDTO
import com.neverland.thinkerbell.data.remote.model.univ.DeptUrlResDTO
import retrofit2.Response
import retrofit2.http.GET

interface UnivService {
    @GET("/api/dept-url")
    suspend fun getDeptUrl(): Response<BaseResponse<List<DeptUrlResDTO>>>

    @GET("/api/dept-contact")
    suspend fun getDeptContact(): Response<BaseResponse<List<DeptContactResDTO>>>
}