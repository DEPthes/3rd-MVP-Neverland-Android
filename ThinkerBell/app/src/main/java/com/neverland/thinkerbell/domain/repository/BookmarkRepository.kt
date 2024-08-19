package com.neverland.thinkerbell.domain.repository

import retrofit2.http.DELETE

interface BookmarkRepository {
    suspend fun postNoticeBookmark(
        category: String,
        noticeId: Int,
        ssaId: String
    ): Result<Boolean>

    @DELETE("/api/bookmark")
    suspend fun deleteNoticeBookmark(
        category: String,
        noticeId: Int,
        ssaId: String
    ): Result<Boolean>
}