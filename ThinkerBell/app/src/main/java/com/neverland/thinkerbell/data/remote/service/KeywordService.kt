package com.neverland.thinkerbell.data.remote.service

import com.neverland.thinkerbell.data.remote.model.BaseResponse
import com.neverland.thinkerbell.data.remote.model.keyword.KeywordDTO
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface KeywordService {
    @GET("/api/keyword")
    suspend fun getKeyword(
        @Query("userSSAID") ssaId: String
    ): Response<BaseResponse<List<KeywordDTO>>>

    @POST("/api/keyword/save")
    suspend fun postKeyword(
        @Query("keyword") keyword: String,
        @Query("userSSAID") ssaId: String
    ): Response<BaseResponse<String>>

    @POST("/api/keyword/delete")
    suspend fun deleteKeyword(
        @Query("keyword") keyword: String,
        @Query("userSSAID") ssaId: String
    ): Response<BaseResponse<String>>
}