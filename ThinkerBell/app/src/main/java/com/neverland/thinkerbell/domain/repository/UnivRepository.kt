package com.neverland.thinkerbell.domain.repository

import com.neverland.thinkerbell.domain.model.univ.AcademicSchedule
import com.neverland.thinkerbell.domain.model.univ.Banner
import com.neverland.thinkerbell.domain.model.univ.DeptContact
import com.neverland.thinkerbell.domain.model.univ.DeptUrl

interface UnivRepository {

    suspend fun getDeptUrl(): Result<List<DeptUrl>>

    suspend fun getDeptContact(): Result<List<DeptContact>>

    suspend fun getMonthlyAcademicSchedule(month: Int, ssaId: String): Result<List<AcademicSchedule>>

    suspend fun getBanner(): Result<List<Banner>>
}