package com.neverland.thinkerbell.domain.usecase.alarm

import com.neverland.thinkerbell.data.repository.AlarmRepositoryImpl

class CheckAlarmUseCase {
    private val repository: AlarmRepositoryImpl = AlarmRepositoryImpl()

    suspend operator fun invoke(ssaId: String, keyword: String): Result<Boolean> {
        val data = repository.checkAlarm(ssaId, keyword).getOrNull()
        return if (data != null){
            Result.success(data)
        } else {
            Result.failure(Exception())
        }
    }
}