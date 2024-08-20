package com.neverland.thinkerbell.domain.usecase.univ

import com.neverland.thinkerbell.data.repository.UnivRepositoryImpl
import com.neverland.thinkerbell.domain.model.univ.AcademicSchedule

class GetAcademicScheduleUseCase {
    private val repository: UnivRepositoryImpl = UnivRepositoryImpl()

    suspend operator fun invoke(month: Int, ssaId: String): Result<List<AcademicSchedule>> {
        return repository.getMonthlyAcademicSchedule(month, ssaId)
    }
}