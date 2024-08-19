package com.neverland.thinkerbell.domain.usecase.notice

import com.neverland.thinkerbell.data.repository.NoticeRepositoryImpl
import com.neverland.thinkerbell.domain.model.PageableNotice
import com.neverland.thinkerbell.domain.model.notice.NoticeItem


class GetJobTrainingNoticesUseCase() {
    private val repository: NoticeRepositoryImpl = NoticeRepositoryImpl()

    suspend operator fun invoke(page: Int, ssaId: String): Result<PageableNotice<NoticeItem.JobNotice>> {
        return repository.getJobTrainingNotices(page = page, ssaId = ssaId)
    }
}