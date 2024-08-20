package com.neverland.thinkerbell.data.remote.service

import com.neverland.thinkerbell.data.remote.model.BaseResponse
import com.neverland.thinkerbell.data.remote.model.alarm.AlarmDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlarmService {
    @GET("/api/alarm/mark-viewed")
    suspend fun readAlarm(
        @Query("alarmId") alarmId: Int
    ): Response<BaseResponse<String>>

    @GET("/api/alarm")
    suspend fun getAlarm(
        @Query("SSAID") ssaId: String,
        @Query("keyword") keyword: String
    ): Response<BaseResponse<List<AlarmDTO>>>

    @GET("/api/alarm/check")
    suspend fun checkAlarm(
        @Query("SSAID") ssaId: String,
        @Query("keyword") keyword: String
    ): Response<BaseResponse<Boolean>>

    @GET("/api/alarm/check-all")
    suspend fun checkAllAlarm(
        @Query("SSAID") ssaId: String
    ): Response<BaseResponse<Boolean>>
}