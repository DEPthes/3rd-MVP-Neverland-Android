package com.neverland.thinkerbell.data.repository

import com.neverland.thinkerbell.data.remote.RetrofitClient
import com.neverland.thinkerbell.data.remote.service.AlarmService
import com.neverland.thinkerbell.domain.model.alarm.Alarm
import com.neverland.thinkerbell.domain.repository.AlarmRepository
import org.json.JSONObject

class AlarmRepositoryImpl: AlarmRepository {
    private val service = RetrofitClient.getInstance().create(AlarmService::class.java)

    override suspend fun readAlarm(alarmId: Int): Result<String> {
        return try {
            val res = service.readAlarm(alarmId)
            if(res.isSuccessful){
                if(res.body() != null) {
                    val data = res.body()!!.data
                    Result.success(data)
                } else {
                    Result.failure(Exception("Read Alarm failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Read Alarm failed: $msg"))
            }
        } catch (e : Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAlarm(ssaId: String, keyword: String): Result<List<Alarm>> {
        return try {
            val res = service.getAlarm(ssaId, keyword)
            if(res.isSuccessful){
                if(res.body() != null) {
                    val data = res.body()!!.data
                    if(data != null) {
                        Result.success(res.body()!!.data.map { Alarm(id = it.id, title = it.title, noticeTypeKorean = it.noticeTypeKorean, noticeTypeEnglish = it.noticeTypeEnglish, viewed = it.viewed, pubDate = it.pubDate, url = it.url, marked = it.marked) })
                    } else {
                        Result.success(emptyList())
                    }
                } else {
                    Result.failure(Exception("Get Alarm failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get Alarm failed: $msg"))
            }
        } catch (e : Exception) {
            Result.failure(e)
        }
    }

    override suspend fun checkAlarm(ssaId: String, keyword: String): Result<Boolean> {
        return try {
            val res = service.checkAlarm(ssaId, keyword)
            if(res.isSuccessful){
                if(res.body() != null) {
                    val data = res.body()!!.data
                    Result.success(data)
                } else {
                    Result.failure(Exception("Check Alarm failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Check Alarm failed: $msg"))
            }
        } catch (e : Exception) {
            Result.failure(e)
        }
    }

    override suspend fun checkAllAlarm(ssaId: String): Result<Boolean> {
        return try {
            val res = service.checkAllAlarm(ssaId)
            if(res.isSuccessful){
                if(res.body() != null) {
                    val data = res.body()!!.data
                    Result.success(data)
                } else {
                    Result.failure(Exception("Check Alarm failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Check Alarm failed: $msg"))
            }
        } catch (e : Exception) {
            Result.failure(e)
        }
    }
}