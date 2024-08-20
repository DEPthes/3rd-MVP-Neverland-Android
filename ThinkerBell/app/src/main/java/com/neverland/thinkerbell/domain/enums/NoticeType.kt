package com.neverland.thinkerbell.domain.enums

enum class NoticeType(val koName: String, val enName: String, val tabName: String) {
    NORMAL_NOTICE("일반공지", "NormalNotice", "일반"),
    EVENT_NOTICE("행사공지", "EventNotice", "행사"),
    ACADEMIC_NOTICE("학사공지", "AcademicNotice", "학사"),
    SCHOLARSHIP_NOTICE("장학/학자금공지", "ScholarshipNotice", "장학/학자금"),
    CAREER_NOTICE("진로/취업/창업공지", "CareerNotice", "취업"),
    STUDENT_ACTS_NOTICE("학생활동공지", "StudentActsNotice", "학생활동"),
    BIDDING_NOTICE("입찰공지", "BiddingNotice", "입찰"),
    SAFETY_NOTICE("대학안전공지", "SafetyNotice", "대학안전"),
    REVISION_NOTICE("학칙개정 사전공고", "RevisionNotice", "학칙개정"),
    DORMITORY_NOTICE("[생활관] 공지사항", "DormitoryNotice", "생활관"),
    DORMITORY_ENTRY_NOTICE("[생활관] 입퇴사공지사항", "DormitoryEntryNotice", "생활관 입퇴사"),
    LIBRARY_NOTICE("[도서관] 공지사항", "LibraryNotice", "도서관"),
    TEACHING_NOTICE("[교직] 공지사항", "TeachingNotice", "교직"),
    JOB_TRAINING_NOTICE("현장실습지원 참여기업 안내", "JobTrainingNotice", "현장실습"),
    ACADEMIC_SCHEDULE("학사일정", "AcademicSchedule", "학사일정")
}
