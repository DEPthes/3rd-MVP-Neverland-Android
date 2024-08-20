package com.neverland.thinkerbell.domain.usecase.keyword

import com.neverland.thinkerbell.data.repository.KeywordRepositoryImpl

class PostKeywordUseCase {
    private val repository: KeywordRepositoryImpl = KeywordRepositoryImpl()

    suspend operator fun invoke(ssaId: String, keyword: String): Result<Boolean> {
        return repository.postKeyword(ssaId = ssaId, keyword = keyword)
    }
}