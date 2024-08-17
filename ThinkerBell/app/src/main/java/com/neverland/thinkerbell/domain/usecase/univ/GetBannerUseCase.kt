package com.neverland.thinkerbell.domain.usecase.univ

import com.neverland.thinkerbell.data.repository.UnivRepositoryImpl
import com.neverland.thinkerbell.domain.model.univ.Banner

class GetBannerUseCase {
    private val repository: UnivRepositoryImpl = UnivRepositoryImpl()

    suspend operator fun invoke(): Result<List<Banner>> {
        return repository.getBanner()
    }
}