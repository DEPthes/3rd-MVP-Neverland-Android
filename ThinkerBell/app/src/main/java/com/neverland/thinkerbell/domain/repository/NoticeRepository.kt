package com.neverland.thinkerbell.domain.repository

import com.neverland.thinkerbell.domain.model.notice.NoticeItem

interface NoticeRepository {

    suspend fun getDormitoryNotices(): Result<List<NoticeItem.CommonNotice>>

    suspend fun getDormitoryEntryNotices(): Result<List<NoticeItem.CommonNotice>>

    suspend fun getJobTrainingNotices(): Result<List<NoticeItem.JobNotice>>

    suspend fun getLibraryNotices(): Result<List<NoticeItem.CommonNotice>>

    suspend fun getTeachingNotices(): Result<List<NoticeItem.CommonNotice>>
}