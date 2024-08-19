package com.neverland.thinkerbell.domain.usecase.bookmark

import com.neverland.thinkerbell.data.repository.BookmarkRepositoryImpl
import com.neverland.thinkerbell.domain.model.notice.RecentBookmarkNotice

class GetRecentBookmarkNoticeUseCase {
    private val repository: BookmarkRepositoryImpl = BookmarkRepositoryImpl()

    suspend operator fun invoke(ssaId: String): Result<List<RecentBookmarkNotice>> {
        val data = repository.getRecentNoticeBookmark(ssaId).getOrNull()
        return if (data != null){
            Result.success(data)
        } else {
            Result.failure(Exception())
        }
    }
}