package com.neverland.thinkerbell.data.repository

import com.neverland.thinkerbell.data.remote.RetrofitClient
import com.neverland.thinkerbell.data.remote.model.user.PostUserInfoReqDTO
import com.neverland.thinkerbell.data.remote.service.UserService
import com.neverland.thinkerbell.domain.repository.UserRepository
import org.json.JSONObject

class UserRepositoryImpl: UserRepository {
    private val service = RetrofitClient.getInstance().create(UserService::class.java)

    override suspend fun postUserInfo(ssaId: String, fcmToken: String): Result<Boolean> {
        return try {
            val res = service.postUserInfo(PostUserInfoReqDTO(ssaId = ssaId, deviceToken = fcmToken))

            if(res.isSuccessful){
                if(res.body() != null){
                    Result.success(true)
                } else {
                    Result.failure(Exception("Post User Info failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Post User Info failed: $msg"))
            }
        } catch (e : Exception){
            Result.failure(e)
        }
    }
}