package com.neverland.thinkerbell.data.repository

import com.neverland.thinkerbell.data.remote.RetrofitClient
import com.neverland.thinkerbell.data.remote.service.UnivService
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
}