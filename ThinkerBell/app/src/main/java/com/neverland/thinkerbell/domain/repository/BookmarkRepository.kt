package com.neverland.thinkerbell.domain.repository

import com.neverland.thinkerbell.domain.model.notice.BookmarkNotice
import retrofit2.http.DELETE

interface BookmarkRepository {

    suspend fun getNoticeBookmark(ssaId: String): Result<BookmarkNotice>

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