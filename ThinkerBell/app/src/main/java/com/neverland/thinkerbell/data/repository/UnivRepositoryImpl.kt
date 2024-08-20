package com.neverland.thinkerbell.data.repository

import com.neverland.thinkerbell.data.remote.RetrofitClient
import com.neverland.thinkerbell.data.remote.service.UnivService
import com.neverland.thinkerbell.domain.model.univ.AcademicSchedule
import com.neverland.thinkerbell.domain.model.univ.Banner
import com.neverland.thinkerbell.domain.model.univ.DeptContact
import com.neverland.thinkerbell.domain.model.univ.DeptUrl
import com.neverland.thinkerbell.domain.repository.UnivRepository
import org.json.JSONObject

class UnivRepositoryImpl: UnivRepository {
    private val service = RetrofitClient.getInstance().create(UnivService::class.java)

    override suspend fun getDeptUrl(): Result<List<DeptUrl>> {
        return try {
            val res = service.getDeptUrl()
            if(res.isSuccessful){
                if(res.body() != null){
                    Result.success(res.body()!!.data.map { DeptUrl(college = it.college, school = it.school, url = it.url) })
                } else {
                    Result.failure(Exception("Get department url failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get department url failed: $msg"))
            }
        } catch (e : Exception){
            Result.failure(e)
        }
    }

    override suspend fun getDeptContact(): Result<List<DeptContact>> {
        return try {
            val res = service.getDeptContact()
            if(res.isSuccessful){
                if(res.body() != null){
                    Result.success(res.body()!!.data.map { DeptContact(college = it.college, campus = it.campus, contact = it.contact, major = it.major) })
                } else {
                    Result.failure(Exception("Get department contact failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get department contact failed: $msg"))
            }
        } catch (e : Exception){
            Result.failure(e)
        }
    }

    override suspend fun getMonthlyAcademicSchedule(month: Int, ssaId: String): Result<List<AcademicSchedule>> {
        return try {
            val res = service.getMonthlyAcademicSchedule(month, ssaId)
            if(res.isSuccessful){
                if(res.body() != null){
                    Result.success(res.body()!!.data.map { AcademicSchedule(id = it.id, title = it.title, marked = it.marked, startDate = it.startDate, endDate = it.endDate) })
                } else {
                    Result.failure(Exception("Get monthly academic schedule failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get monthly academic schedule failed: $msg"))
            }
        } catch (e : Exception){
            Result.failure(e)
        }
    }

    override suspend fun getBanner(): Result<List<Banner>> {
        return try {
            val res = service.getBanner()
            if(res.isSuccessful){
                if(res.body() != null){
                    Result.success(res.body()!!.data.map { Banner(id = it.id, title = it.title, s3Url = it.s3Url, noticeUrl = it.noticeUrl) })
                } else {
                    Result.failure(Exception("Get banner failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get banner failed: $msg"))
            }
        } catch (e : Exception){
            Result.failure(e)
        }
    }
}