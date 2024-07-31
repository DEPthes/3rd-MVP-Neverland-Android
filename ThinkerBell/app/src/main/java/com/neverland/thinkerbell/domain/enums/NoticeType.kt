package com.neverland.thinkerbell.domain.enums

enum class NoticeType(val title: String) {
    GENERAL("일반공지"),
    EVENT("행사공지"),
    ACADEMIC("학사공지"),
    SCHOLARSHIP_STUDENT_LOAN("장학/학자금공지"),
    CAREER_EMPLOYMENT("진로/취업/창업공지"),
    STUDENT_ACTIVITIES("학생활동공지"),
    BIDDING("입찰공지"),
    UNIV_SAFETY("대학안전공지"),
    REGULATION("학칙개정 사전공고"),
    DORMITORY("[생활관] 공지사항"),
    DORMITORY_ENTRANCE_EXIT("[생활관] 입퇴사공지사항"),
    LIBRARY("[도서관] 공지사항"),
    TEACHING("[교직] 공지사항"),
    JOB_TRAINING("현장실습지원 참여기업 안내")
}
