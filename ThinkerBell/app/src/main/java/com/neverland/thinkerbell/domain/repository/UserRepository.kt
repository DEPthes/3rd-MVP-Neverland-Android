package com.neverland.thinkerbell.domain.repository


interface UserRepository {

    suspend fun postUserInfo(ssaId: String, fcmToken: String): Result<Boolean>
}