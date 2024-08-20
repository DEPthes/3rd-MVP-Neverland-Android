package com.neverland.thinkerbell.domain.usecase.notice

import com.neverland.thinkerbell.data.repository.NoticeRepositoryImpl
import com.neverland.thinkerbell.domain.model.notice.RecentNotices

class GetRecentNoticesUseCase {
    private val repository: NoticeRepositoryImpl = NoticeRepositoryImpl()

    suspend operator fun invoke(ssaId: String): Result<RecentNotices> {
        return repository.getRecentNotices(ssaId = ssaId)
    }
}