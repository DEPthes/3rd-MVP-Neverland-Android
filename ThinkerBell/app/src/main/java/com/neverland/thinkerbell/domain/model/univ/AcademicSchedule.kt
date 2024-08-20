package com.neverland.thinkerbell.domain.model.univ

data class AcademicSchedule(
    val id: Int,
    val title: String,
    val startDate: String,
    val endDate: String,
    val marked: Boolean
)
