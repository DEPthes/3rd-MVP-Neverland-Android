package com.neverland.thinkerbell.domain.enums

enum class NoticeType(val koName: String, val enName: String) {
    NORMAL_NOTICE("일반공지", "NormalNotice"),
    EVENT_NOTICE("행사공지", "EventNotice"),
    ACADEMIC_NOTICE("학사공지", "AcademicNotice"),
    SCHOLARSHIP_NOTICE("장학/학자금공지", "ScholarshipNotice"),
    CAREER_NOTICE("진로/취업/창업공지", "CareerNotice"),
    STUDENT_ACTS_NOTICE("학생활동공지", "StudentActsNotice"),
    BIDDING_NOTICE("입찰공지", "BiddingNotice"),
    SAFETY_NOTICE("대학안전공지", "SafetyNotice"),
    REVISION_NOTICE("학칙개정 사전공고", "RevisionNotice"),
    DORMITORY_NOTICE("[생활관] 공지사항", "DormitoryNotice"),
    DORMITORY_ENTRY_NOTICE("[생활관] 입퇴사공지사항", "DormitoryEntryNotice"),
    LIBRARY_NOTICE("[도서관] 공지사항", "LibraryNotice"),
    TEACHING_NOTICE("[교직] 공지사항", "TeachingNotice"),
    JOB_TRAINING_NOTICE("현장실습지원 참여기업 안내", "JobTrainingNotice")
}
