package com.neverland.thinkerbell.domain.usecase.notice

import com.neverland.thinkerbell.data.repository.DataStoreRepositoryImpl
import com.neverland.thinkerbell.data.repository.NoticeRepositoryImpl
import com.neverland.thinkerbell.data.repository.UnivRepositoryImpl
import com.neverland.thinkerbell.domain.model.notice.NoticeItem
import com.neverland.thinkerbell.domain.model.univ.DeptUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class GetLibraryNoticesUseCase() {
    private val repository: NoticeRepositoryImpl = NoticeRepositoryImpl()

    suspend operator fun invoke(): Result<List<NoticeItem.CommonNotice>> {
        return repository.getLibraryNotices()
    }
}