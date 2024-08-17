package com.neverland.thinkerbell.domain.repository

import retrofit2.http.DELETE

interface BookmarkRepository {
    suspend fun postNoticeBookmark(
        category: String,
        noticeId: String,
        ssaId: String
    ): Result<Boolean>

    @DELETE("/api/bookmark")
    suspend fun deleteNoticeBookmark(
        category: String,
        noticeId: String,
        ssaId: String
    ): Result<Boolean>
}