package com.neverland.thinkerbell.domain.usecase.notice

import com.neverland.thinkerbell.data.repository.NoticeRepositoryImpl
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.model.notice.AllNotices
import com.neverland.thinkerbell.domain.model.notice.NoticeItem

class SearchAllNoticesByCategoryUseCase {
    private val repository: NoticeRepositoryImpl = NoticeRepositoryImpl()

    suspend operator fun invoke(keyword: String, ssaId: String): Result<Map<NoticeType, List<NoticeItem>>> {
        val data = repository.searchAllNoticesByCategory(keyword, ssaId).getOrNull()
        return if (data != null){
            Result.success(getNonEmptyListsWithItems(data))
        } else {
            Result.failure(Exception())
        }
    }

    private fun getNonEmptyListsWithItems(allNotices: AllNotices): Map<NoticeType, List<NoticeItem>> {
        val nonEmptyListsWithItems = mutableMapOf<NoticeType, List<NoticeItem>>()
        if (!allNotices.safetyNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.SAFETY_NOTICE] = allNotices.safetyNotice
        }

        if (!allNotices.revisionNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.REVISION_NOTICE] = allNotices.revisionNotice
        }

        if (!allNotices.libraryNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.LIBRARY_NOTICE] = allNotices.libraryNotice
        }

        if (!allNotices.dormitoryNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.DORMITORY_NOTICE] = allNotices.dormitoryNotice
        }

        if (!allNotices.teachingNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.TEACHING_NOTICE] = allNotices.teachingNotice
        }

        if (!allNotices.jobTrainingNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.JOB_TRAINING_NOTICE] = allNotices.jobTrainingNotice
        }

        if (!allNotices.eventNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.EVENT_NOTICE] = allNotices.eventNotice
        }

        if (!allNotices.studentActsNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.STUDENT_ACTS_NOTICE] = allNotices.studentActsNotice
        }

        if (!allNotices.academicNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.ACADEMIC_NOTICE] = allNotices.academicNotice
        }

        if (!allNotices.careerNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.CAREER_NOTICE] = allNotices.careerNotice
        }

        if (!allNotices.biddingNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.BIDDING_NOTICE] = allNotices.biddingNotice
        }

        if (!allNotices.dormitoryEntryNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.DORMITORY_ENTRY_NOTICE] = allNotices.dormitoryEntryNotice
        }

        if (!allNotices.scholarshipNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.SCHOLARSHIP_NOTICE] = allNotices.scholarshipNotice
        }

        if (!allNotices.normalNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.NORMAL_NOTICE] = allNotices.normalNotice
        }
        return nonEmptyListsWithItems
    }
}