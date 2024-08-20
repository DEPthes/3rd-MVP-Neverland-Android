package com.neverland.thinkerbell.domain.repository

import com.neverland.thinkerbell.domain.model.notice.BookmarkNotice
import com.neverland.thinkerbell.domain.model.notice.RecentBookmarkNotice
import retrofit2.http.DELETE

interface BookmarkRepository {

    suspend fun getNoticeBookmark(ssaId: String): Result<BookmarkNotice>

    suspend fun getRecentNoticeBookmark(ssaId: String): Result<List<RecentBookmarkNotice>>

    suspend fun postNoticeBookmark(
        category: String,
        noticeId: Int,
        ssaId: String
    ): Result<Boolean>

    suspend fun deleteNoticeBookmark(
        category: String,
        noticeId: Int,
        ssaId: String
    ): Result<Boolean>
}