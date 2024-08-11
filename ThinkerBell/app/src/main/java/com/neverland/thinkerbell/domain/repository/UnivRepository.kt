package com.neverland.thinkerbell.domain.repository

import com.neverland.thinkerbell.domain.model.univ.DeptContact
import com.neverland.thinkerbell.domain.model.univ.DeptUrl

interface UnivRepository {

    suspend fun getDeptUrl(): Result<List<DeptUrl>>

    suspend fun getDeptContact(): Result<List<DeptContact>>
}