package com.neverland.thinkerbell.domain.usecase.notice

import com.neverland.thinkerbell.data.repository.NoticeRepositoryImpl
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.model.notice.NoticeItem

class SearchNoticesByCategoryUseCase {
    private val repository: NoticeRepositoryImpl = NoticeRepositoryImpl()

    suspend operator fun invoke(noticeType: NoticeType, keyword: String, ssaId: String): Result<List<NoticeItem>> {
        return repository.searchNoticesByCategory(noticeType = noticeType, keyword = keyword, ssaId = ssaId)
    }
}