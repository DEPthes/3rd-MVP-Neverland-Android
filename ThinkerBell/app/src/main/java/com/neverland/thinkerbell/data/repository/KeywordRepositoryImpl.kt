package com.neverland.thinkerbell.data.repository

import com.neverland.thinkerbell.data.remote.RetrofitClient
import com.neverland.thinkerbell.data.remote.service.KeywordService
import com.neverland.thinkerbell.domain.model.keyword.Keyword
import com.neverland.thinkerbell.domain.repository.KeywordRepository
import org.json.JSONObject

class KeywordRepositoryImpl: KeywordRepository {
    private val service = RetrofitClient.getInstance().create(KeywordService::class.java)
    override suspend fun getKeyword(ssaId: String): Result<List<Keyword>> {
        return try {
            val res = service.getKeyword(ssaId)
            if(res.isSuccessful){
                if(res.body() != null) {
                    val data = res.body()!!.data
                    if(data != null) {
                        Result.success(res.body()!!.data.map { Keyword(keyword = it.keyword) })
                    } else {
                        Result.success(emptyList())
                    }
                } else {
                    Result.failure(Exception("Get Keyword failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Get Keyword failed: $msg"))
            }
        } catch (e : Exception) {
            Result.failure(e)
        }
    }

    override suspend fun postKeyword(keyword: String, ssaId: String): Result<Boolean> {
        return try {
            val res = service.postKeyword(keyword, ssaId)
            if(res.isSuccessful){
                if(res.body() != null){
                    if(res.body()!!.code == 200){
                        Result.success(true)
                    } else {
                        Result.failure(Exception(res.body()!!.data))
                    }
                } else {
                    Result.failure(Exception("Post Keyword failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Post Keyword failed: $msg"))
            }
        } catch (e : Exception){
            Result.failure(e)
        }
    }

    override suspend fun deleteKeyword(keyword: String, ssaId: String): Result<Boolean> {
        return try {
            val res = service.deleteKeyword(keyword, ssaId)
            if(res.isSuccessful){
                if(res.body() != null){
                    if(res.body()!!.code == 200){
                        Result.success(true)
                    } else {
                        Result.failure(Exception(res.body()!!.data))
                    }
                } else {
                    Result.failure(Exception("Delete Keyword failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Delete Keyword failed: $msg"))
            }
        } catch (e : Exception){
            Result.failure(e)
        }
    }
}