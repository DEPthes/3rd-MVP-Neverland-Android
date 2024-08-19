package com.neverland.thinkerbell.data.repository

import com.neverland.thinkerbell.data.remote.RetrofitClient
import com.neverland.thinkerbell.data.remote.model.user.PostUserInfoReqDTO
import com.neverland.thinkerbell.data.remote.service.BookmarkService
import com.neverland.thinkerbell.domain.repository.BookmarkRepository
import org.json.JSONObject

class BookmarkRepositoryImpl: BookmarkRepository {
    private val service = RetrofitClient.getInstance().create(BookmarkService::class.java)

    override suspend fun postNoticeBookmark(
        category: String,
        noticeId: Int,
        ssaId: String
    ): Result<Boolean> {
        return try {
            val res = service.postNoticeBookmark(category, noticeId, ssaId)
            if(res.isSuccessful){
                if(res.body() != null){
                    Result.success(true)
                } else {
                    Result.failure(Exception("Post Bookmark failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Post Bookmark failed: $msg"))
            }
        } catch (e : Exception){
            Result.failure(e)
        }
    }

    override suspend fun deleteNoticeBookmark(
        category: String,
        noticeId: Int,
        ssaId: String
    ): Result<Boolean> {
        return try {
            val res = service.deleteNoticeBookmark(category, noticeId, ssaId)
            if(res.isSuccessful){
                if(res.body() != null){
                    Result.success(true)
                } else {
                    Result.failure(Exception("Delete Bookmark failed: response is null data"))
                }
            } else {
                val jsonObject = JSONObject(res.errorBody().toString())
                val msg = jsonObject.getString("message")
                Result.failure(Exception("Delete Bookmark failed: $msg"))
            }
        } catch (e : Exception){
            Result.failure(e)
        }
    }

}