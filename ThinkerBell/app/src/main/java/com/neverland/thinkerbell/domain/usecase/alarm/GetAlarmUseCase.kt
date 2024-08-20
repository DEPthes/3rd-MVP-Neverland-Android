package com.neverland.thinkerbell.domain.usecase.alarm

import com.neverland.thinkerbell.data.repository.AlarmRepositoryImpl
import com.neverland.thinkerbell.domain.model.alarm.Alarm
import com.neverland.thinkerbell.domain.repository.AlarmRepository

class GetAlarmUseCase {
    private val repository: AlarmRepository = AlarmRepositoryImpl()

    suspend operator fun invoke(ssaId: String, keyword: String): Result<List<Alarm>> {
        val data = repository.getAlarm(ssaId, keyword).getOrNull()
        return if (data != null){
            Result.success(data)
        } else {
            Result.failure(Exception())
        }
    }
}