package com.neverland.thinkerbell.domain.repository

import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.model.PageableNotice
import com.neverland.thinkerbell.domain.model.notice.AllNotices
import com.neverland.thinkerbell.domain.model.notice.NoticeItem
import com.neverland.thinkerbell.domain.model.notice.RecentNotices

interface NoticeRepository {

    suspend fun getDormitoryNotices(page: Int, ssaId: String, campus: String): Result<PageableNotice<NoticeItem.CommonNotice>>

    suspend fun getDormitoryEntryNotices(page: Int, ssaId: String, campus: String): Result<PageableNotice<NoticeItem.CommonNotice>>

    suspend fun getJobTrainingNotices(page: Int, ssaId: String): Result<PageableNotice<NoticeItem.JobNotice>>

    suspend fun getLibraryNotices(page: Int, ssaId: String, campus: String): Result<PageableNotice<NoticeItem.CommonNotice>>

    suspend fun getTeachingNotices(page: Int, ssaId: String): Result<PageableNotice<NoticeItem.CommonNotice>>

    suspend fun getAcademicNotices(page: Int, ssaId: String): Result<PageableNotice<NoticeItem.CommonNotice>>

    suspend fun getEventNotices(page: Int, ssaId: String): Result<PageableNotice<NoticeItem.CommonNotice>>

    suspend fun getNormalNotices(page: Int, ssaId: String): Result<PageableNotice<NoticeItem.CommonNotice>>

    suspend fun getRevisionNotices(page: Int, ssaId: String): Result<PageableNotice<NoticeItem.CommonNotice>>

    suspend fun getSafetyNotices(page: Int, ssaId: String): Result<PageableNotice<NoticeItem.CommonNotice>>

    suspend fun getScholarshipNotices(page: Int, ssaId: String): Result<PageableNotice<NoticeItem.CommonNotice>>

    suspend fun getStudentActsNotices(page: Int, ssaId: String): Result<PageableNotice<NoticeItem.CommonNotice>>

    suspend fun getCareerNotices(page: Int, ssaId: String): Result<PageableNotice<NoticeItem.CommonNotice>>

    suspend fun getBiddingNotices(page: Int, ssaId: String): Result<PageableNotice<NoticeItem.CommonNotice>>

    suspend fun searchNoticesByCategory(noticeType: NoticeType, keyword: String, ssaId: String): Result<List<NoticeItem>>

    suspend fun searchAllNoticesByCategory(keyword: String, ssaId: String): Result<AllNotices>

    suspend fun getRecentNotices(ssaId: String): Result<RecentNotices>
}