package com.neverland.thinkerbell.domain.usecase.bookmark

import com.neverland.thinkerbell.data.repository.BookmarkRepositoryImpl
import com.neverland.thinkerbell.domain.enums.NoticeType
import com.neverland.thinkerbell.domain.model.notice.BookmarkNotice
import com.neverland.thinkerbell.domain.model.notice.NoticeItem

class GetBookmarkNoticeUseCase {
    private val repository: BookmarkRepositoryImpl = BookmarkRepositoryImpl()

    suspend operator fun invoke(ssaId: String): Result<Map<NoticeType, List<NoticeItem>>> {
        val data = repository.getNoticeBookmark(ssaId).getOrNull()
        return if (data != null){
            Result.success(getNonEmptyListsWithItems(data))
        } else {
            Result.failure(Exception())
        }
    }

    private fun getNonEmptyListsWithItems(bookmarkNotice: BookmarkNotice): Map<NoticeType, List<NoticeItem>> {
        val nonEmptyListsWithItems = mutableMapOf<NoticeType, List<NoticeItem>>()

        if (!bookmarkNotice.safetyNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.SAFETY_NOTICE] = bookmarkNotice.safetyNotice
        }

        if (!bookmarkNotice.revisionNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.REVISION_NOTICE] = bookmarkNotice.revisionNotice
        }

        if (!bookmarkNotice.libraryNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.LIBRARY_NOTICE] = bookmarkNotice.libraryNotice
        }

        if (!bookmarkNotice.dormitoryNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.DORMITORY_NOTICE] = bookmarkNotice.dormitoryNotice
        }

        if (!bookmarkNotice.teachingNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.TEACHING_NOTICE] = bookmarkNotice.teachingNotice
        }

        if (!bookmarkNotice.jobTrainingNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.JOB_TRAINING_NOTICE] = bookmarkNotice.jobTrainingNotice
        }

        if (!bookmarkNotice.eventNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.EVENT_NOTICE] = bookmarkNotice.eventNotice
        }

        if (!bookmarkNotice.studentActsNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.STUDENT_ACTS_NOTICE] = bookmarkNotice.studentActsNotice
        }

        if (!bookmarkNotice.academicNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.ACADEMIC_NOTICE] = bookmarkNotice.academicNotice
        }

        if (!bookmarkNotice.careerNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.CAREER_NOTICE] = bookmarkNotice.careerNotice
        }

        if (!bookmarkNotice.biddingNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.BIDDING_NOTICE] = bookmarkNotice.biddingNotice
        }

        if (!bookmarkNotice.dormitoryEntryNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.DORMITORY_ENTRY_NOTICE] = bookmarkNotice.dormitoryEntryNotice
        }

        if (!bookmarkNotice.scholarshipNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.SCHOLARSHIP_NOTICE] = bookmarkNotice.scholarshipNotice
        }

        if (!bookmarkNotice.normalNotice.isNullOrEmpty()) {
            nonEmptyListsWithItems[NoticeType.NORMAL_NOTICE] = bookmarkNotice.normalNotice
        }

        return nonEmptyListsWithItems
    }

}