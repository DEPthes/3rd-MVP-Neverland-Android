package com.neverland.thinkerbell.domain.usecase.keyword

import com.neverland.thinkerbell.data.repository.KeywordRepositoryImpl

class DeleteKeywordUseCase {
    private val repository: KeywordRepositoryImpl = KeywordRepositoryImpl()

    suspend operator fun invoke(ssaId: String, keyword: String): Result<Boolean> {
        return repository.deleteKeyword(ssaId = ssaId, keyword = keyword)
    }
}