package com.neverland.thinkerbell.data.repository

import com.neverland.thinkerbell.data.remote.RetrofitClient
import com.neverland.thinkerbell.data.remote.service.NoticeService
import com.neverland.thinkerbell.domain.model.notice.NoticeItem
import com.neverland.thinkerbell.domain.model.univ.DeptContact
import com.neverland.thinkerbell.domain.repository.NoticeRepository
import org.json.JSONObject

class NoticeRepositoryImpl: NoticeRepository {
    private val service = RetrofitClient.getInstance().create(NoticeService::class.java)

    override suspend fun getDormitoryNotices(): Result<List<NoticeItem.CommonNotice>> {
        return try {
            val res = service.getDormitoryNotices()
            if(res.isSuccessful){
                if(res.body() != null){
                    Result.success(res.body()!!.data.map { NoticeItem.CommonNotice(
                        id = it.id,
                        title = it.title,
                        pubDate = it.pubDate,
                        url = it.url,
                        campus = it.campus,
                        important = it.important
                    ) })
                } else {
                    Result.failure(Exception("Get DormitoryNotices failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get DormitoryNotices failed: $msg"))
            }
        } catch (e : Exception){
            Result.failure(e)
        }
    }

    override suspend fun getDormitoryEntryNotices(): Result<List<NoticeItem.CommonNotice>> {
        return try {
            val res = service.getDormitoryEntryNotices()
            if(res.isSuccessful){
                if(res.body() != null){
                    Result.success(res.body()!!.data.map { NoticeItem.CommonNotice(
                        id = it.id,
                        title = it.title,
                        pubDate = it.pubDate,
                        url = it.url,
                        campus = it.campus,
                        important = it.important
                    ) })
                } else {
                    Result.failure(Exception("Get DormitoryNotices failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get DormitoryNotices failed: $msg"))
            }
        } catch (e : Exception){
            Result.failure(e)
        }
    }

    override suspend fun getJobTrainingNotices(): Result<List<NoticeItem.JobNotice>> {
        return try {
            val res = service.getJobTrainingNotices()
            if(res.isSuccessful){
                if(res.body() != null){
                    Result.success(res.body()!!.data.map { NoticeItem.JobNotice(
                        company = it.company,
                        year = it.year,
                        semester = it.semester,
                        recruitingNum = it.recrutingNum,
                        major = it.major,
                        deadline = it.deadline,
                        period = it.period,
                        jobName = it.jobName
                    ) })
                } else {
                    Result.failure(Exception("Get JobTrainingNotices failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get JobTrainingNotices failed: $msg"))
            }
        } catch (e : Exception){
            Result.failure(e)
        }
    }

    override suspend fun getLibraryNotices(): Result<List<NoticeItem.CommonNotice>> {
        return try {
            val res = service.getLibraryNotices()
            if(res.isSuccessful){
                if(res.body() != null){
                    Result.success(res.body()!!.data.map { NoticeItem.CommonNotice(
                        id = it.id,
                        title = it.title,
                        pubDate = it.pubDate,
                        url = it.url,
                        campus = it.campus,
                    ) })
                } else {
                    Result.failure(Exception("Get LibraryNotices failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get LibraryNotices failed: $msg"))
            }
        } catch (e : Exception){
            Result.failure(e)
        }
    }

    override suspend fun getTeachingNotices(): Result<List<NoticeItem.CommonNotice>> {
        return try {
            val res = service.getTeachingNotices()
            if(res.isSuccessful){
                if(res.body() != null){
                    Result.success(res.body()!!.data.map { NoticeItem.CommonNotice(
                        id = it.id,
                        title = it.title,
                        pubDate = it.pubDate,
                        url = it.url,
                        important = it.important,
                    ) })
                } else {
                    Result.failure(Exception("Get TeachingNotices failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get TeachingNotices failed: $msg"))
            }
        } catch (e : Exception){
            Result.failure(e)
        }
    }
}