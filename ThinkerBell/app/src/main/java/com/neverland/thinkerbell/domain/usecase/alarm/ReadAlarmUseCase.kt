package com.neverland.thinkerbell.domain.usecase.alarm

import com.neverland.thinkerbell.data.repository.AlarmRepositoryImpl
import com.neverland.thinkerbell.domain.repository.AlarmRepository

class ReadAlarmUseCase {
    private val repository: AlarmRepository = AlarmRepositoryImpl()

    suspend operator fun invoke(alarmId: Int): Result<String> {
        val data = repository.readAlarm(alarmId).getOrNull()
        return if (data != null){
            Result.success(data)
        } else {
            Result.failure(Exception())
        }
    }
}