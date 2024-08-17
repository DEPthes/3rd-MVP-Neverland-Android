package com.neverland.thinkerbell.domain.usecase.user

import com.neverland.thinkerbell.data.repository.UserRepositoryImpl

class PostUserInfoUseCase {
    private val repository: UserRepositoryImpl = UserRepositoryImpl()

    suspend operator fun invoke(ssaId: String, fcmToken: String): Result<Boolean> {
        return repository.postUserInfo(ssaId, fcmToken)
    }
}