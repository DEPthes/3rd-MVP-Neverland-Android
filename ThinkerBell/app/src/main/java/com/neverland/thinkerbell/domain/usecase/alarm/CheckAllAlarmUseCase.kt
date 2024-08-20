package com.neverland.thinkerbell.domain.usecase.alarm

import com.neverland.thinkerbell.data.repository.AlarmRepositoryImpl

class CheckAllAlarmUseCase {
    private val repository: AlarmRepositoryImpl = AlarmRepositoryImpl()

    suspend operator fun invoke(ssaId: String): Result<Boolean> {
        return repository.checkAllAlarm(ssaId)
    }
}