package com.neverland.thinkerbell.domain.usecase.bookmark

import com.neverland.thinkerbell.data.repository.BookmarkRepositoryImpl
import com.neverland.thinkerbell.domain.enums.NoticeType

class DeleteBookmarkUseCase {
    private val repository: BookmarkRepositoryImpl = BookmarkRepositoryImpl()

    suspend operator fun invoke(ssaId: String, category: NoticeType, noticeId: Int): Result<Boolean> {
        return repository.deleteNoticeBookmark(ssaId = ssaId, category = category.enName, noticeId = noticeId)
    }
}