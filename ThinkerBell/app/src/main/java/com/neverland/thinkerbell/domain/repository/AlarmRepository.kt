package com.neverland.thinkerbell.domain.repository

import com.neverland.thinkerbell.domain.model.alarm.Alarm

interface AlarmRepository {

    suspend fun readAlarm(alarmId: Int): Result<String>

    suspend fun getAlarm(ssaId: String, keyword: String): Result<List<Alarm>>

    suspend fun checkAlarm(ssaId: String, keyword: String): Result<Boolean>

    suspend fun checkAllAlarm(ssaId: String): Result<Boolean>

}